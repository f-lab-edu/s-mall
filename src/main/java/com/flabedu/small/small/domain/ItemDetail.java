package com.flabedu.small.small.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDetail {

    private long itemDetailId;
    private long itemId;
    private long stock;
    private String size;

}
