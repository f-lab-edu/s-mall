package com.flabedu.small.small.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemErrorCodes implements ErrorCodes {
    CANNOT_FIND_ITEM("cannot find item"),
    CANNOT_FIND_ITEM_DETAIL("cannot find item_detail"),
    NO_STOCK("no item stock")
    ;
    final String code;
}
