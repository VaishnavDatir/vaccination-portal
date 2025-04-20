package com.school.vaccineportal.vaccination_portal.model;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private HttpStatus status;
    private Integer totalCount; // Optional - only for paginated responses

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message, T data, HttpStatus status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public static <T> ApiResponse<T> success(T data, HttpStatus status) {
        return new ApiResponse<>(true, "Success", data, status);
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return new ApiResponse<>(false, message, null, status);
    }

    public static <T> ApiResponse<T> success(T data, HttpStatus status, Integer totalCount) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Success");
        response.setData(data);
        response.setStatus(status);
        response.setTotalCount(totalCount);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ApiResponse [success=" + success + ", message=" + message + ", data=" + data + ", status=" + status
                + ", totalCount=" + totalCount + "]";
    }

}
