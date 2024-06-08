package kz.edu.astanait.authentiactionservice.dto;

import kz.edu.astanait.authentiactionservice.model.enums.Role;
import lombok.Data;

import java.util.Set;

/**
 * @author aldi
 * @since 15.05.2024
 */

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
    private Set<Role> roles;
    private Boolean receiveTgNotification;
    private Boolean receiveEmailNotification;
}
