package com.kritica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//This is a global exception handler
@RestControllerAdvice
public class ProductManagementExceptionHandler {

    //defining exception so that when ever exception
    //  occurred, spring boot handles it in the below-defined way
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgNotValidException(MethodArgumentNotValidException e) {

        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            response.put(fieldName, errorMessage);

        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

