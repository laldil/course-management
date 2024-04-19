package kz.edu.astanait.quizservice.repository;

import kz.edu.astanait.quizservice.model.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author aldi
 * @since 11.02.2024
 */
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByIdIn(List<Long> id);
}
