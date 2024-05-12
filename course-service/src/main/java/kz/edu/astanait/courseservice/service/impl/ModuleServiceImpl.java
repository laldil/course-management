package kz.edu.astanait.courseservice.service.impl;

import kz.edu.astanait.courseservice.dto.ModuleRequestDto;
import kz.edu.astanait.courseservice.mapper.ModuleMapper;
import kz.edu.astanait.courseservice.model.CourseEntity;
import kz.edu.astanait.courseservice.model.ModuleEntity;
import kz.edu.astanait.courseservice.repository.CourseRepository;
import kz.edu.astanait.courseservice.repository.ModuleRepository;
import kz.edu.astanait.courseservice.service.AttachmentService;
import kz.edu.astanait.courseservice.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author aldi
 * @since 21.04.2024
 */
@RequiredArgsConstructor
@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    private final AttachmentService attachmentService;

    @Transactional
    @Override
    public ModuleEntity create(ModuleRequestDto request, Long courseId) {
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        ModuleEntity module = ModuleMapper.INSTANCE.mapToEntity(request);
        module.setCourse(course);
        ModuleEntity savedModule = moduleRepository.save(module);

        request.getAttachments().forEach(createAttachmentRequest -> attachmentService.create(createAttachmentRequest, savedModule));

        return savedModule;
    }

    @Transactional
    @Override
    public Boolean delete(Long moduleId, Long courseId) {
        moduleRepository.deleteById(moduleId);
        return true;
    }
}
