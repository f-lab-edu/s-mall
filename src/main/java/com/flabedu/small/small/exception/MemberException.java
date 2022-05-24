package com.flabedu.small.small.exception;

public class MemberException extends BaseException{
    public MemberException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
