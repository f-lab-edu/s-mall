package com.flabedu.small.small.model;

import com.flabedu.small.small.model.enums.OrderStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Orders {
    private long orderId;
    private long memberId;
    private BigDecimal totalPrice;
    private LocalDateTime ordersDate;
    private OrderStatus status;
    private LocalDateTime modifiedDate;
}
