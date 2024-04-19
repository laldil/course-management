package kz.edu.astanait.courseservice.repository;

import kz.edu.astanait.courseservice.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author aldi
 * @since 10.02.2024
 */
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findByIdIn(List<Long> ids);
}
