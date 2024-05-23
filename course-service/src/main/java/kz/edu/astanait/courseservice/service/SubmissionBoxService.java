package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.client.FileClient;
import kz.edu.astanait.courseservice.client.UserClient;
import kz.edu.astanait.courseservice.dto.submission.CreateSubmissionBoxDto;
import kz.edu.astanait.courseservice.dto.submission.FullSubmissionBoxDto;
import kz.edu.astanait.courseservice.dto.submission.SubmissionBoxDto;
import kz.edu.astanait.courseservice.mapper.SubmissionBoxMapper;
import kz.edu.astanait.courseservice.repository.ModuleRepository;
import kz.edu.astanait.courseservice.repository.SubmissionBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author aldi
 * @since 21.05.2024
 */

@RequiredArgsConstructor
@Service
public class SubmissionBoxService {
    private final SubmissionBoxRepository submissionBoxRepository;
    private final ModuleRepository moduleRepository;

    private final UserClient userClient;
    private final FileClient fileClient;

    public SubmissionBoxDto create(CreateSubmissionBoxDto dto, Long moduleId) {
        var module = moduleRepository.findById(moduleId).orElseThrow(() -> new RuntimeException("Module not found"));

        var box = SubmissionBoxMapper.INSTANCE.mapToEntity(dto);
        box.setModule(module);
        var savedBox = submissionBoxRepository.save(box);

        return SubmissionBoxMapper.INSTANCE.mapToDto(savedBox);
    }

    public Boolean delete(Long id) {
        var submission = submissionBoxRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission not found"));
        submissionBoxRepository.delete(submission);
        return true;
    }

    public FullSubmissionBoxDto get(Long id) {
        var box = submissionBoxRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission box not found"));
        return SubmissionBoxMapper.INSTANCE.mapToFullDto(box, userClient, fileClient);
    }

    public SubmissionBoxDto getShortInfo(Long id) {
        var box = submissionBoxRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission box not found"));
        return SubmissionBoxMapper.INSTANCE.mapToDto(box);
    }
}
