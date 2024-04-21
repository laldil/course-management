package kz.edu.astanait.courseservice.repository;

import kz.edu.astanait.courseservice.model.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aldi
 * @since 21.04.2024
 */
public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {
}
