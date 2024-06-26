package kz.edu.astanait.authentiactionservice.dto;

import kz.edu.astanait.authentiactionservice.model.enums.Role;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author aldi
 * @since 23.03.2024
 */
@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
