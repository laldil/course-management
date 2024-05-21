package kz.edu.astanait.courseservice.repository;

import kz.edu.astanait.courseservice.model.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aldi
 * @since 21.05.2024
 */
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
}
