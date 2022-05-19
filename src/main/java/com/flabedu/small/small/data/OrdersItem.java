package com.flabedu.small.small.data;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OrdersItem {
    long ordersId;
    long itemId;
    long itemDetailId;
    long ordersItemCount;
    long price;

    public OrdersItem(long ordersId, long itemId, long itemDetailId, long ordersItemCount, long price) {
        this.ordersId = ordersId;
        this.itemId = itemId;
        this.itemDetailId = itemDetailId;
        this.ordersItemCount = ordersItemCount;
        this.price = price;
    }
}
