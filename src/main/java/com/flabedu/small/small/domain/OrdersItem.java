package com.flabedu.small.small.domain;

import lombok.*;

@Data
public class OrdersItem {
    final long ordersId;
    final long itemId;
    final long itemDetailId;
    final long ordersItemCount;
    final long price;
}
