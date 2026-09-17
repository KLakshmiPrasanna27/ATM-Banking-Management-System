package com.atm_project.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Map<String, String>> handleInsufficientBalance(
            InsufficientBalanceException e) {

        Map<String, String> response = new HashMap<>();

        response.put("message", e.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Map<String, String>> handleInvalidAmount(
            InvalidAmountException e) {

        Map<String, String> response = new HashMap<>();

        response.put("message", e.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(
            UserNotFoundException e) {

        Map<String, String> response = new HashMap<>();

        response.put("message", e.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(InvalidPinException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPin(
            InvalidPinException e) {

        Map<String, String> response = new HashMap<>();

        response.put("message", e.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.UNAUTHORIZED
        );
    }
    @ExceptionHandler(InvalidOldPinException.class)
    public ResponseEntity<Map<String, String>> handleInvalidOldPin(
            InvalidOldPinException e) {

        Map<String, String> response = new HashMap<>();

        response.put("message", e.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.UNAUTHORIZED
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(
            Exception e) {

        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}