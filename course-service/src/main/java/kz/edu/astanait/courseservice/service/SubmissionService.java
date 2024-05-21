package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.client.FileClient;
import kz.edu.astanait.courseservice.client.UserClient;
import kz.edu.astanait.courseservice.dto.submission.CreateSubmissionDto;
import kz.edu.astanait.courseservice.dto.submission.SubmissionDto;
import kz.edu.astanait.courseservice.mapper.SubmissionMapper;
import kz.edu.astanait.courseservice.model.SubmissionEntity;
import kz.edu.astanait.courseservice.model.enums.SubmissionStatus;
import kz.edu.astanait.courseservice.repository.SubmissionBoxRepository;
import kz.edu.astanait.courseservice.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author aldi
 * @since 21.05.2024
 */

@RequiredArgsConstructor
@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionBoxRepository submissionBoxRepository;

    private final FileClient fileClient;
    private final UserClient userClient;

    public SubmissionDto create(CreateSubmissionDto dto, Long submissionBoxId) {
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
        submission.getFileIds().forEach(fileClient::deleteFile);
        return true;
    }
}
