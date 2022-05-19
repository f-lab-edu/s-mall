package com.flabedu.small.small.data;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access= AccessLevel.PUBLIC)
public class ItemDetail {
    long itemDetailId;
    long itemId;
    long stock;
    String size;
}
