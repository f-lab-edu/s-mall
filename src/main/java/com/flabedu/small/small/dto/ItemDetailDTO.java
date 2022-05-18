package com.flabedu.small.small.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDetailDTO {

    private long itemDetailId;
    private long itemId;
    private long stock;
    private String size;

}
