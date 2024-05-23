package kz.edu.astanait.courseservice.repository;

import kz.edu.astanait.courseservice.model.SubmissionBoxEntity;
import kz.edu.astanait.courseservice.model.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author aldi
 * @since 21.05.2024
 */
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
    Optional<SubmissionEntity> findBySubmissionBoxAndUploadedById(SubmissionBoxEntity submissionBox, Long uploadedById);
}
