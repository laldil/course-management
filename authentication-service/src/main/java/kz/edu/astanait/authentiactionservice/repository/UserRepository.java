package kz.edu.astanait.authentiactionservice.repository;

import kz.edu.astanait.authentiactionservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author aldi
 * @since 19.03.2024
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<UserEntity> findByIdIn(List<Long> id);

    @Query("select distinct u from UserEntity u join u.roles r where r.role IN :roles")
    List<UserEntity> findByRoleNames(@Param("roles") List<String> roles);
}
