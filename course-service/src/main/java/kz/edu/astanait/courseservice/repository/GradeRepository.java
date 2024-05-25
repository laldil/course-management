package kz.edu.astanait.courseservice.repository;

import kz.edu.astanait.courseservice.model.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aldi
 * @since 25.05.2024
 */
public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
}
