package com.moukiladev.novelblog.exception;

import com.moukiladev.novelblog.dto.ExceptionDtoResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDtoResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        ExceptionDtoResponse dto = new ExceptionDtoResponse("Resource not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(dto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDtoResponse> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->"Enter a correct value of the field "+ error.getField())
                .findFirst()
                .orElse("Invalid input");

        ExceptionDtoResponse dto = new ExceptionDtoResponse(message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(dto);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionDtoResponse> handleResourceAlreadyExistsException
            (ResourceAlreadyExistsException e) {
        ExceptionDtoResponse dto = new ExceptionDtoResponse("This resource already exists");
        return  ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(dto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDtoResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ExceptionDtoResponse dto = new ExceptionDtoResponse("This entry conflicts with existing record");
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(dto);
    }

}
