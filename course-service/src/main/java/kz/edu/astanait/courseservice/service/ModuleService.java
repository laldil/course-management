package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.dto.CreateModuleRequest;
import kz.edu.astanait.courseservice.model.ModuleEntity;

/**
 * @author aldi
 * @since 21.04.2024
 */
public interface ModuleService {
    ModuleEntity create(CreateModuleRequest request, Long courseId);
}
