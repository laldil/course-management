package kz.edu.astanait.scoreservice.repository;

import kz.edu.astanait.scoreservice.models.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author aldi
 * @since 01.06.2024
 */
public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
    Optional<ScoreEntity> findByUserId(Long userId);

    List<ScoreEntity> findTop20ByOrderByAllTimeScoreDesc();
}
