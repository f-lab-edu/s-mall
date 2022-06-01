package com.flabedu.small.small.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    NOT_FOUND_ID(600, "가입되지 않은 ID입니다."),
    MISMATCH_PASSWORD(601, "잘못된 비밀번호입니다."),
    DUPLICATED_ID(603, "중복된 ID입니다."),
    INVALID_PASSWORD(604, "잘못된 비밀번호 양식입니다."),

    INVALID_VALUE_ARGUMENT(700, "요청 파라미터의 값이 유효하지 않습니다."),
    INVALID_TYPE_ARGUMENT(701, "요청 파라미터의 타입이 올바르지 않습니다."),
    NOT_ENOUGH_STOCK(702, "해당 상품의 재고가 부족합니다.");

    private int code;
    private String message;
}
