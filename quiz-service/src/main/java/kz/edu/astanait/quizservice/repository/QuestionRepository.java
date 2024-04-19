package kz.edu.astanait.quizservice.repository;

import kz.edu.astanait.quizservice.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author aldi
 * @since 11.02.2024
 */
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByIdIn(List<Long> id);
}
