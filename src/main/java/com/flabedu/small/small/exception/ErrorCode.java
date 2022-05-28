package com.flabedu.small.small.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_ID(409, "중복된 ID입니다."),
    NOT_FOUND_ID(400, "가입되지 않은 ID입니다."),
    MISMATCH_PASSWORD(400, "잘못된 비밀번호입니다."),
    INVALID_PASSWORD(400, "잘못된 비밀번호 양식입니다."),
    INVALID_ARGUMENT(400, "요청 파라미터가 유효하지 않습니다."),
    NOT_ENOUGH_STOCK(400, "해당 상품의 재고가 부족합니다.");

    private int status;
    private String message;
}
