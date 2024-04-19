package kz.edu.astanait.authentiactionservice.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author aldi
 * @since 23.03.2024
 */
@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
}
