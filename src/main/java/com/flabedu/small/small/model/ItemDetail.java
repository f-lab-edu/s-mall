package com.flabedu.small.small.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ItemDetail {

    private Long itemDetailId;
    private Long itemId;
    private Long stock;
    private SizeEnum size;

}
