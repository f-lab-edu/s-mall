package com.flabedu.small.small.data;

import lombok.*;

@Data
@AllArgsConstructor
public class ItemDetail {
    long itemDetailId;
    long itemId;
    long stock;
    String size;
}
