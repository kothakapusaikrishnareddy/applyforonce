package com.applyforonce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(EmailAlreadyExistsException e, HttpServletRequest request){

        ErrorResponse errorResponse =
                new ErrorResponse(
                        Instant.now(),
                        HttpStatus.CONFLICT.value(),
                        "EMAIL_ALREADY_EXISTS",
                        e.getMessage(),
                        request.getRequestURI()
                );

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(errorResponse);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e,  HttpServletRequest request){

        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));

        ErrorResponse errorResponse =
                new ErrorResponse(
                        Instant.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "INVALID_INPUT",
                        errorMessage,
                        request.getRequestURI()
                );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(
            Exception e,
            HttpServletRequest request) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        Instant.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "INTERNAL_SERVER_ERROR",
                        "An unexpected error occurred",
                        request.getRequestURI()
                );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
