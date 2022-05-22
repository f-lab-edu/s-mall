package com.flabedu.small.small.exception;

public class CannotFindItemException extends ItemException {
    public CannotFindItemException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
