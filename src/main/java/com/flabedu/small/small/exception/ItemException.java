package com.flabedu.small.small.exception;

public class ItemException extends BaseException{

    public ItemException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
