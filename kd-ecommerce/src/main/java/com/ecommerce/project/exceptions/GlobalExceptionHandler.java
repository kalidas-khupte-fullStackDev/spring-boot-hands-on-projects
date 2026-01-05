package com.ecommerce.project.exceptions;

import com.ecommerce.project.exceptions.custom.APIException;
import com.ecommerce.project.exceptions.custom.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String,String> exceptionResponse = new HashMap<String,String>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(ex -> {
           String fieldName = ((FieldError)ex).getField();
           String exMessage = ex.getDefaultMessage();
           exceptionResponse.put(fieldName,exMessage);
        });
        return new ResponseEntity<>(exceptionResponse, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        String exMessage = resourceNotFoundException.getMessage();
        return new ResponseEntity<>(exMessage, HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }
    
    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> handleResourceNotFoundException(APIException exception){
        String exMessage = exception.getMessage();
        return new ResponseEntity<>(exMessage, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

}
