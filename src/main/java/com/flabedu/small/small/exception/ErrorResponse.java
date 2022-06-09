package com.flabedu.small.small.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorResponse {
    private int code;
    private String message;
    private List<CustomFieldError> error;
}
