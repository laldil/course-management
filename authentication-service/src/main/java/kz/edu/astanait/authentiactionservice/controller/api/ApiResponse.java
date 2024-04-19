package kz.edu.astanait.authentiactionservice.controller.api;

import lombok.Data;

/**
 * @author aldi
 * @since 24.03.2024
 */

@Data
public abstract class ApiResponse {
    private Boolean success;
    private String msg = "OK";
}
