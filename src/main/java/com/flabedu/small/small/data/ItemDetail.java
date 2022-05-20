package com.flabedu.small.small.data;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@Builder(access= AccessLevel.PUBLIC)
public class ItemDetail {
    long itemDetailId;
    long itemId;
    long stock;
    String size;
}
