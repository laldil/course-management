package kz.edu.astanait.authentiactionservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kz.edu.astanait.authentiactionservice.model.enums.Role;
import kz.edu.astanait.authentiactionservice.model.enums.VerificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author aldi
 * @since 06.06.2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verification_tokens")
public class VerificationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "verification_token")
    private String token;

    @Column(name = "tg_id")
    private Long tgId;

    @Column(name = "verification_type")
    @Enumerated(EnumType.STRING)
    private VerificationType verificationType;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
