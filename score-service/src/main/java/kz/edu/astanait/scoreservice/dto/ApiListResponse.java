package kz.edu.astanait.scoreservice.dto;

import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 02.06.2024
 */
@Data
public class ApiListResponse<T> {
    private Boolean success;
    private String msg;
    private List<T> list;
}