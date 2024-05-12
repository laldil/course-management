package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.dto.ModuleRequestDto;
import kz.edu.astanait.courseservice.model.ModuleEntity;

/**
 * @author aldi
 * @since 21.04.2024
 */
public interface ModuleService {
    ModuleEntity create(ModuleRequestDto request, Long courseId);

    Boolean delete(Long moduleId, Long courseId);

}
