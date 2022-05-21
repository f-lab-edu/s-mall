package com.flabedu.small.small.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDetail {

    private long itemDetailId;
    private long itemId;
    private long stock;
    private String size;

}
