package com.flabedu.small.small.exception;

public class CannotFindItemDetailException extends ItemException{
    public CannotFindItemDetailException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
