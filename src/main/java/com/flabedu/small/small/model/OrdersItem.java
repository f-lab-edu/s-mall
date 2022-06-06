package com.flabedu.small.small.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrdersItem {
    private final long ordersId;
    private final long itemId;
    private final long itemDetailId;
    private final long ordersItemCount;
    private final BigDecimal price;
}
