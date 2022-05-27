package com.flabedu.small.small.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDetail {
    private Long itemDetailId;
    private Long itemId;
    private SizeEnum size;
    private Long stock;
}
