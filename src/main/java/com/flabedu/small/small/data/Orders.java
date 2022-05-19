package com.flabedu.small.small.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
public class Orders {
    long orderId;
    long memberId;
    long totalPrice;
    OrderStatus status;
    LocalDateTime ordersDate;
    LocalDateTime modifiedDate;

    public Orders(long memberId, long totalPrice, OrderStatus status, LocalDateTime ordersDate, LocalDateTime modifiedDate) {
        this(0, memberId,totalPrice,status,ordersDate,modifiedDate);
    }

    public Orders(long orderId, long memberId, long totalPrice, OrderStatus status, LocalDateTime ordersDate, LocalDateTime modifiedDate) {
        this.memberId = memberId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.ordersDate = ordersDate;
        this.modifiedDate = modifiedDate;
    }

    @Getter
    @AllArgsConstructor
    public static enum OrderStatus{
        SUCCEED("S"), CANCEL("C");
        @NotNull
        String status;
    }
}
