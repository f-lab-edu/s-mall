package com.flabedu.small.small.model;

import com.flabedu.small.small.model.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Orders {
    private final long orderId;
    private final long memberId;
    private final BigDecimal totalPrice;
    private final LocalDateTime ordersDate;
    private final OrderStatus status;
    private final LocalDateTime modifiedDate;


}
