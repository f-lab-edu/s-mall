package com.flabedu.small.small.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class FailResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;

}
