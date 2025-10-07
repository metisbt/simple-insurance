package com.example.simpleinsurance.exception;

import com.example.simpleinsurance.api.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException (ResourceNotFoundException ex, WebRequest request){
        log.warn("ResourceNotFoundException occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ApiResponse.notFound(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException (Exception ex, WebRequest request) {
        log.error("An unexpected error occurred: {}",  ex.getMessage(), ex);
        return new ResponseEntity<>(ApiResponse.error("An unexpected error occurred.", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.joining(", "));
        log.warn("Validation failed for request: {}", errors);
        return new ResponseEntity<>(ApiResponse.error("Validation failed", errors, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
