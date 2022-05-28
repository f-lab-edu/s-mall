package com.flabedu.small.small.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes{
    CANNOT_FIND_ITEM(400, "cannot find item"),
    CANNOT_FIND_ITEM_DETAIL(400, "cannot find item_detail"),
    NO_STOCK(400, "no item stock"),
    CANNOT_FIND_USER(403, "cannot find user"),
    INVALID_ARGUMENTS(400, "invalid arguments"),
    ;
    private final int code;
    private final String message;

}
