package com.flabedu.small.small.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidValueException(MethodArgumentNotValidException e){
        CustomErrorCode errorCode = CustomErrorCode.INVALID_VALUE_ARGUMENT;

        List<CustomFieldError> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(er -> new CustomFieldError(er.getField(),er.getRejectedValue(),er.getDefaultMessage()))
                .collect(Collectors.toList());

        CustomErrorResponse response = CustomErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .error(fieldErrors).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleInvalidTypeException(HttpMessageNotReadableException e){
        CustomErrorCode errorCode = CustomErrorCode.INVALID_TYPE_ARGUMENT;

        CustomErrorResponse response = CustomErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e){
        CustomErrorResponse response = CustomErrorResponse.builder()
                .code(e.getErrorCode().getCode())
                .message(e.getErrorCode().getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
