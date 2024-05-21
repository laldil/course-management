package kz.edu.astanait.courseservice.repository;

import kz.edu.astanait.courseservice.model.SubmissionBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aldi
 * @since 21.05.2024
 */
public interface SubmissionBoxRepository extends JpaRepository<SubmissionBoxEntity, Long> {
}
