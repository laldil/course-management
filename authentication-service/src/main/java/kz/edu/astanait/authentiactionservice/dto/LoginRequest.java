package kz.edu.astanait.authentiactionservice.dto;

import lombok.Data;

/**
 * @author aldi
 * @since 23.03.2024
 */
@Data
public class LoginRequest {
    private String email;
    private String password;
}
