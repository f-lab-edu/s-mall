package com.flabedu.small.small.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes{
    // Item 에러는 700번대, Member 에러는 600번대, Order 에러는 800번대
    CANNOT_FIND_ITEM(700, "cannot find item"),
    CANNOT_FIND_ITEM_DETAIL(701, "cannot find item_detail"),
    NO_STOCK(800, "no item stock"),
    CANNOT_FIND_USER(600, "cannot find user")
    ;
    private final int code;
    private final String message;

}
