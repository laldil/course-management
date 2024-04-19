package kz.edu.astanait.authentiactionservice.repository;

import kz.edu.astanait.authentiactionservice.model.RoleEntity;
import kz.edu.astanait.authentiactionservice.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author aldi
 * @since 24.03.2024
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRole(Role role);

    List<RoleEntity> findByRoleIn(List<Role> role);
}
