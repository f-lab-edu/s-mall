package com.flabedu.small.small.web.exception_handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    final String code;
    final String message;
}
