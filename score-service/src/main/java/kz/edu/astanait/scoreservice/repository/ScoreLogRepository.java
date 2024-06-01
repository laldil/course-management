package kz.edu.astanait.scoreservice.repository;

import kz.edu.astanait.scoreservice.models.ScoreLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aldi
 * @since 01.06.2024
 */
public interface ScoreLogRepository extends JpaRepository<ScoreLogEntity, Long> {
}
