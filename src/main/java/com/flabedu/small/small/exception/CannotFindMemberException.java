package com.flabedu.small.small.exception;

public class CannotFindMemberException extends MemberException {
    public CannotFindMemberException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
