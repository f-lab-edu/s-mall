package com.flabedu.small.small.dao;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class OrdersItemDao {
    private Long ordersId;
    private Long itemId;
    private Long itemDetailId;
    private long ordersItemCount;
    private BigDecimal price;
}
