package com.flabedu.small.small.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException{
    protected final ErrorCodes errorCode;
    protected final String message;
}
