package com.flabedu.small.small.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCodes implements ErrorCodes {
    CANNOT_FIND_USER("cannot find user"),
    ;
    final String code;
}
