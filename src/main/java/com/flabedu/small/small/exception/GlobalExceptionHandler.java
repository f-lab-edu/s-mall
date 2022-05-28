package com.flabedu.small.small.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex){
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;

        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream().map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        FailResponse response = FailResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .timestamp(LocalDateTime.now())
                .errors(errors).build();

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleException(CustomException e){
        FailResponse response = FailResponse.builder()
                .status(e.getErrorCode().getStatus())
                .message(e.getErrorCode().getMessage())
                .timestamp(LocalDateTime.now())
                .errors(null).build();

        return ResponseEntity.status(e.getErrorCode().getStatus()).body(response);
    }

}
