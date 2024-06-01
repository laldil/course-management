package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.client.FileClient;
import kz.edu.astanait.courseservice.client.ScoreClient;
import kz.edu.astanait.courseservice.client.UserClient;
import kz.edu.astanait.courseservice.dto.GradeDto;
import kz.edu.astanait.courseservice.dto.UpdateScoreRequest;
import kz.edu.astanait.courseservice.dto.enums.ScoreTransactionType;
import kz.edu.astanait.courseservice.dto.submission.CreateSubmissionDto;
import kz.edu.astanait.courseservice.dto.submission.SubmissionDto;
import kz.edu.astanait.courseservice.dto.submission.UpdateSubmissionRequest;
import kz.edu.astanait.courseservice.mapper.GradeMapper;
import kz.edu.astanait.courseservice.mapper.SubmissionMapper;
import kz.edu.astanait.courseservice.model.SubmissionEntity;
import kz.edu.astanait.courseservice.model.enums.SubmissionStatus;
import kz.edu.astanait.courseservice.repository.GradeRepository;
import kz.edu.astanait.courseservice.repository.SubmissionBoxRepository;
import kz.edu.astanait.courseservice.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author aldi
 * @since 21.05.2024
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionBoxRepository submissionBoxRepository;
    private final GradeRepository gradeRepository;

    private final FileClient fileClient;
    private final UserClient userClient;
    private final ScoreClient scoreClient;

    public SubmissionDto create(CreateSubmissionDto dto, Long submissionBoxId) {
        Boolean exists = submissionRepository.existsByUploadedByIdAndSubmissionBoxId(dto.getUploadedById(), submissionBoxId);
        if (exists) {
            throw new RuntimeException("Submission already exists");
        }

        var box = submissionBoxRepository.findById(submissionBoxId).orElseThrow(() -> new RuntimeException("Submission box not found"));
        var submission = SubmissionMapper.INSTANCE.mapToEntity(dto);

        submission.setSubmissionBox(box);
        submission.setStatus(SubmissionStatus.getStatus(box.getDueDate(), submission.getUploadDate()));
        SubmissionEntity saved = submissionRepository.save(submission);

        return SubmissionMapper.INSTANCE.mapToDto(saved, fileClient, userClient);
    }

    public Boolean delete(Long id) {
        var submission = submissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission not found"));
        submissionRepository.delete(submission);

        CompletableFuture.runAsync(() -> submission.getFileIds().forEach(fileClient::deleteFile));

        return true;
    }

    public SubmissionDto get(Long submissionBoxId, Long userId) {
        var box = submissionBoxRepository.findById(submissionBoxId)
                .orElseThrow(() -> new RuntimeException("Submission box not found"));
        var submission = submissionRepository.findBySubmissionBoxAndUploadedById(box, userId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        return SubmissionMapper.INSTANCE.mapToDto(submission, fileClient, userClient);
    }

    public SubmissionDto update(UpdateSubmissionRequest request, Long submissionBoxId) {
        var box = submissionBoxRepository.findById(submissionBoxId)
                .orElseThrow(() -> new RuntimeException("Submission box not found"));
        var submission = submissionRepository.findBySubmissionBoxAndUploadedById(box, request.getUploadedById())
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        SubmissionMapper.INSTANCE.updateEntity(request, submission);
        var saved = submissionRepository.save(submission);
        return SubmissionMapper.INSTANCE.mapToDto(saved, fileClient, userClient);
    }

    public SubmissionDto setGrade(Long id, GradeDto dto) {
        var submission = submissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission not found"));

        var grade = GradeMapper.INSTANCE.mapToEntity(dto, submission);
        var savedGrade = gradeRepository.save(grade);

        submission.setGrade(savedGrade);
        if (Objects.isNull(submission.getScored()) || !submission.getScored()) {
            try {
                scoreClient.update(new UpdateScoreRequest(submission.getUploadedById(), submission.getGrade().getGrade(), ScoreTransactionType.ADD));
                submission.setScored(true);
            } catch (Exception e) {
                submission.setScored(false);
                log.error(e.getMessage());
            }
        }

        var savedSubmission = submissionRepository.save(submission);

        return SubmissionMapper.INSTANCE.mapToDto(savedSubmission, fileClient, userClient);
    }
}
