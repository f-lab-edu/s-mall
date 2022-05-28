package com.flabedu.small.small.model;

import com.flabedu.small.small.model.enums.SizeEnum;
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
