package kz.edu.astanait.authentiactionservice.controller.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author aldi
 * @since 24.03.2024
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiListResponse<T> extends ApiResponse {
    private List<T> list;
    int totalSize;

    public static <E> ApiListResponse<E> create(List<E> list) {
        ApiListResponse<E> response = new ApiListResponse<>();
        response.setSuccess(true);
        response.setList(list);
        response.setTotalSize(list.size());
        return response;
    }

    public static <E> ApiListResponse<E> create(List<E> list, int totalSize) {
        ApiListResponse<E> response = new ApiListResponse<>();
        response.setSuccess(true);
        response.setList(list);
        response.setTotalSize(totalSize);
        return response;
    }

    public static <E> ApiListResponse<E> failed(String msg) {
        ApiListResponse<E> response = new ApiListResponse<>();
        response.setSuccess(false);
        response.setMsg(msg);
        return response;
    }
}
