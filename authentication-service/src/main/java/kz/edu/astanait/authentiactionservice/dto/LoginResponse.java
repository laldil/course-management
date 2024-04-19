package kz.edu.astanait.authentiactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author aldi
 * @since 23.03.2024
 */
@AllArgsConstructor
@Data
public class LoginResponse {
    private UserProfileDto user;
    private String tokenType;
    private String accessToken;
}
