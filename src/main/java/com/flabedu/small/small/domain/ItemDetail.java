package com.flabedu.small.small.domain;

import lombok.*;

@Data
public class ItemDetail {
    final long itemDetailId;
    final long itemId;
    final long stock;
    final String size;
}
