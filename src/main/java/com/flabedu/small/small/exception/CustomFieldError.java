package com.flabedu.small.small.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomFieldError {
    private String fieldName;
    private Object rejectedValue;
    private String fieldErrorMessages;
}
