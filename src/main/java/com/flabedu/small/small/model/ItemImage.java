package com.flabedu.small.small.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemImage {
    private Long itemImageId;
    private Long itemId;
    private Long seq;
    private String filename;
}
