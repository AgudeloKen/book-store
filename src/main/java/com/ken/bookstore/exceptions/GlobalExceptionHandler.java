package com.ken.bookstore.exceptions;

import com.ken.bookstore.responses.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException exception){
        return ResponseEntity.status(404).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> alreadyExistsExceptionHandler(AlreadyExistsException exception){
        return ResponseEntity.status(400).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidHandler(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(AnErrorHasOccurredException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler(AnErrorHasOccurredException exception){
        return ResponseEntity.status(400).body(new ErrorResponse(exception.getMessage()));
    }
}
