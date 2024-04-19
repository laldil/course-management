package kz.edu.astanait.authentiactionservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kz.edu.astanait.authentiactionservice.model.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author aldi
 * @since 24.03.2024
 */
@Setter
@Getter
@Entity
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", unique = true)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
