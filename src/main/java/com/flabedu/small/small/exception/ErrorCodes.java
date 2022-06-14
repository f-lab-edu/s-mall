package com.flabedu.small.small.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes{
    // Item 에러는 700번대, Member 에러는 600번대, Order 에러는 800번대
    CANNOT_FIND_ITEM(700, "cannot find item"),
    CANNOT_FIND_ITEM_DETAIL(701, "cannot find item_detail"),
    ITEM_INVALID_ARGUMENT(702, "요청 파라미터의 값이 유효하지 않습니다."),
    NO_STOCK(800, "no item stock"),
    CANNOT_FIND_USER(600, "cannot find user"),
    MISMATCH_PASSWORD(601, "잘못된 비밀번호입니다."),
    DUPLICATED_ID(603, "중복된 ID입니다."),
    INVALID_PASSWORD(604, "잘못된 비밀번호 양식입니다."),
    ;
    private final int code;
    private final String message;

}
