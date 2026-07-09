package com.spendsense.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(

            ResourceNotFoundException ex,

            HttpServletRequest request) {

        ApiError error = ApiError.builder()

                .timestamp(LocalDateTime.now())

                .status(HttpStatus.NOT_FOUND.value())

                .error(ex.getMessage())

                .path(request.getRequestURI())

                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)

                .body(error);

    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicate(

            DuplicateResourceException ex,

            HttpServletRequest request) {

        ApiError error = ApiError.builder()

                .timestamp(LocalDateTime.now())

                .status(HttpStatus.CONFLICT.value())

                .error(ex.getMessage())

                .path(request.getRequestURI())

                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)

                .body(error);

    }

}