package com.flabedu.small.small.web.exception_handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    final com.flabedu.small.small.exception.ErrorResponse error;
}
