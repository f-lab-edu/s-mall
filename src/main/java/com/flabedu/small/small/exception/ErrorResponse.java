package com.flabedu.small.small.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    final ErrorCodes code;
    final String message;
}
