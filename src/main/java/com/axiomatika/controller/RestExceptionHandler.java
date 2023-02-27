package com.axiomatika.controller;

import com.axiomatika.exception.QuadraticEquationCalculationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(QuadraticEquationCalculationException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(QuadraticEquationCalculationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
