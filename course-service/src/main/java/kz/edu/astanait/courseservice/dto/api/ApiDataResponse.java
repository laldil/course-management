package kz.edu.astanait.courseservice.dto.api;

import lombok.Data;

/**
 * @author aldi
 * @since 01.04.2024
 */
@Data
public class ApiDataResponse<T> {
    private Boolean success;
    private String msg;
    private T data;
}
