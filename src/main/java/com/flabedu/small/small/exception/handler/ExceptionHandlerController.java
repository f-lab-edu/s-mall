package com.flabedu.small.small.exception.handler;

import com.flabedu.small.small.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> handleItemDetail(CustomException e){
        return getErrorResponse(HttpStatus.BAD_REQUEST, e);
    }

    private ResponseEntity<Object> getErrorResponse(HttpStatus status, CustomException e){
        return ResponseEntity.status(status).body(new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage()));
    }

}
