package kz.edu.astanait.quizservice.repository;

import kz.edu.astanait.quizservice.model.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    List<QuizEntity> findByIdIn(List<Long> id);
}
