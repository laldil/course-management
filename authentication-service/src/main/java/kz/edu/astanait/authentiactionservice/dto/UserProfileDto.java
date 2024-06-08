package kz.edu.astanait.authentiactionservice.dto;

import kz.edu.astanait.authentiactionservice.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author aldi
 * @since 25.03.2024
 */

@AllArgsConstructor
@Data
public class UserProfileDto {
    private Long id;
    private String name;
    private String email;
    private Set<Role> roles;
    private List<Long> courseIds;
    private String tgUserName;
    private Boolean receiveTgNotification;
    private Boolean receiveEmailNotification;
    private BigDecimal currentScore;
    private BigDecimal allTimeScore;
}
