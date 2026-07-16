package com.moukiladev.novelblog.exception;

import org.springframework.http.HttpStatus;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> onNotFoundExceptionRaised(ResourceNotFoundException r) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(r.getMessage());
    }
}
