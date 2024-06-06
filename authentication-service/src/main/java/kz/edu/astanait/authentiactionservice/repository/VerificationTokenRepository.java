package kz.edu.astanait.authentiactionservice.repository;

import kz.edu.astanait.authentiactionservice.model.VerificationTokenEntity;
import kz.edu.astanait.authentiactionservice.model.enums.VerificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author aldi
 * @since 06.06.2024
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
    Optional<VerificationTokenEntity> findByTgIdAndTokenAndVerificationType(Long tgId, String token, VerificationType verificationType);
}
