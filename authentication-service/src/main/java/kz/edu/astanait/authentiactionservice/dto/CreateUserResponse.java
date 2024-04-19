package kz.edu.astanait.authentiactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * @author aldi
 * @since 23.03.2024
 */
@AllArgsConstructor
@Data
public class CreateUserResponse {
    private Long id;
    private String name;
    private String email;
}
