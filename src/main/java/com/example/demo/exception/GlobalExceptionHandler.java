package com.example.demo.exception;

import com.example.demo.api.rest.ResponseWrapper;
import com.example.demo.exception.customexception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<?>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ResponseWrapper<?> response = new ResponseWrapper<>();
        response.setMessage(errors);
        response.setStatus(false);
        response.setCode("invalid_request");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseWrapper<?>> handleResourceNotFoundException(ResourceNotFoundException e) {
        ResponseWrapper<?> response = new ResponseWrapper<>();
        response.setMessage(e.getMessage());
        response.setStatus(false);
        response.setCode("not_found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<?>> handleUnwantedException(Exception e) {
        // use logger
        e.printStackTrace();

        ResponseWrapper<?> response = new ResponseWrapper<>();
        response.setMessage("Unknow error");
        response.setStatus(false);
        response.setCode("internal_server_error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
