package com.flabedu.small.small.data;

import lombok.*;

@Data
@AllArgsConstructor
public class OrdersItem {
    long ordersId;
    long itemId;
    long itemDetailId;
    long ordersItemCount;
    long price;
}
