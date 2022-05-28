package com.flabedu.small.small.model;

import lombok.*;

@Data
public class ItemDetail {
    private final long itemDetailId;
    private final long itemId;
    private final long stock;
    private final String size;
}
