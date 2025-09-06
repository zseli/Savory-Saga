package com.jwtauthentication.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler(InvalidRecipeException.class)
    public ResponseEntity<?> invalidRecipeException(InvalidRecipeException ex, WebRequest request) {
        String errorMessage = extractErrorMessageFromException(ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessage, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private String extractErrorMessageFromException(InvalidRecipeException ex) {
        String errorMessage = "Unknown error occurred";
        String[] parts = ex.getMessage().split(":");
        if (parts.length > 1) {
            errorMessage = parts[1].trim();
        }
        return errorMessage;
    }
}