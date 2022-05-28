package com.flabedu.small.small.model;

import lombok.*;

@Data
public class OrdersItem {
    private final long ordersId;
    private final long itemId;
    private final long itemDetailId;
    private final long ordersItemCount;
    private final long price;
}
