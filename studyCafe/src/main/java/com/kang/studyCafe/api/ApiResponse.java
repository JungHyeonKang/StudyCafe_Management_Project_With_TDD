package com.kang.studyCafe.api;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private int code;

    private String message;

    private HttpStatus httpStatus;

    private T data;

    @Builder
    public ApiResponse(String message, HttpStatus httpStatus, T data) {
        this.code = httpStatus.value();
        this.message = message;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, T data) {
        return of(httpStatus.name(), httpStatus, data);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return of(HttpStatus.OK.name(), HttpStatus.OK, data);
    }

    public static <T> ApiResponse<T> of(String message, HttpStatus httpStatus, T data) {
        return ApiResponse.<T>builder()
                .message(message)
                .httpStatus(httpStatus)
                .data(data)
                .build();
    }



}
