package com.flabedu.small.small.exception;

public class NoStockException extends ItemException {
    public NoStockException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
