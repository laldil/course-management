package kz.edu.astanait.courseservice.dto.api;

import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 01.04.2024
 */
@Data
public class ApiListResponse<T> {
    private Boolean success;
    private String msg;
    private List<T> list;
}
