package dev.profitsoft.hw5.controllers;

import dev.profitsoft.hw5.dtos.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorDetails errorDetails = new ErrorDetails("Illegal argument", List.of(e.getMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<String> validationErrorsDetails = new ArrayList<>();

        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            validationErrorsDetails.add(((FieldError) error).getField() + ": " + error.getDefaultMessage());
        }

        ErrorDetails errorDetails = new ErrorDetails("Validation failed", validationErrorsDetails);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
}