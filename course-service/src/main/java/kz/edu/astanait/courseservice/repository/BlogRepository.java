package kz.edu.astanait.courseservice.repository;

import kz.edu.astanait.courseservice.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aldi
 * @since 27.05.2024
 */
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
}
