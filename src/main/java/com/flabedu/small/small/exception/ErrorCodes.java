package com.flabedu.small.small.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes {
    MISMATCH_PASSWORD(601, "잘못된 비밀번호입니다."),
    DUPLICATED_ID(603, "중복된 ID입니다."),
    INVALID_PASSWORD(604, "잘못된 비밀번호 양식입니다."),

    ITEM_INVALID_ARGUMENT(702, "요청 파라미터의 값이 유효하지 않습니다.");

    private final int code;
    private final String message;
}
