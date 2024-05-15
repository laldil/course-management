package kz.edu.astanait.authentiactionservice.dto;

import kz.edu.astanait.authentiactionservice.model.enums.Role;
import lombok.Data;

/**
 * @author aldi
 * @since 15.05.2024
 */

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
    private Role role;
}
