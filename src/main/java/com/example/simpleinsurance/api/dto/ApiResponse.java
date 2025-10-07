package com.example.simpleinsurance.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ApiResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;
    private final String errorMessage;
    private final HttpStatus errorCode;
    private final LocalDateTime timestamp;
    private final String traceId;


    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .traceId(UUID.randomUUID().toString())
                .build();
    }

    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .timestamp(LocalDateTime.now())
                .traceId(UUID.randomUUID().toString())
                .build();
    }

    public static <T> ApiResponse<T> error(String message, String errorMessage, HttpStatus errorCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errorMessage(errorMessage)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .traceId(UUID.randomUUID().toString())
                .build();
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errorMessage("Resource not found")
                .errorCode(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .traceId(UUID.randomUUID().toString())
                .build();
    }

//    private CustomStatus status;
//    private int statusCode;
//    private String message;
//    private String errorMessage;
//    private T details;
//    private LocalDateTime timestamp;
//
//
//    public static <T> ApiResponse<T> success(int statusCode,T data,  String message, LocalDateTime timestamp) {
//        return new ApiResponse<>(CustomStatus.SUCCESS, statusCode, message, null, data, timestamp);
//    }
//
//    public static <T> ApiResponse<T> error(int statusCode, String errorMessage,  String message, LocalDateTime timestamp){
//        return new ApiResponse<>(CustomStatus.FAILURE, statusCode, message, errorMessage, null, timestamp);
//    }
//
//    public static <T> ApiResponse<T> notFound(int statusCode, String message, LocalDateTime timestamp){
//        return new ApiResponse<>(CustomStatus.NOTFOUND, statusCode, message, "Resource not found", null, timestamp);
//    }
}
