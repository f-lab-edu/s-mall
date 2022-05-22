package com.flabedu.small.small.web.exception_handler;

import com.flabedu.small.small.exception.CannotFindItemDetailException;
import com.flabedu.small.small.exception.CannotFindItemException;
import com.flabedu.small.small.exception.ItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CannotFindItemDetailException.class, CannotFindItemException.class})
    public ResponseEntity<Object> handleItemDetail(ItemException e){
        return getErrorResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler()
    public ResponseEntity<Object> handleCannotFindItemDetail(CannotFindItemException e){
        return getErrorResponse(HttpStatus.BAD_REQUEST, e);
    }

    private ResponseEntity<Object> getErrorResponse(HttpStatus status, Exception e){
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), e.getMessage()));
    }

}
