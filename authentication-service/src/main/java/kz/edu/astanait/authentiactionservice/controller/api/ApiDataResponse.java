package kz.edu.astanait.authentiactionservice.controller.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aldi
 * @since 24.03.2024
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiDataResponse<T> extends ApiResponse {
    private T data;

    public ApiDataResponse(T data) {
        this.data = data;
        setSuccess(true);
    }

    public ApiDataResponse() {
    }

    public static <E> ApiDataResponse<E> create(E data) {
        return new ApiDataResponse<>(data);
    }

    public static <E> ApiDataResponse<E> failed(String msg) {
        ApiDataResponse<E> response = new ApiDataResponse<>();
        response.setSuccess(false);
        response.setMsg(msg);
        return response;
    }
}
