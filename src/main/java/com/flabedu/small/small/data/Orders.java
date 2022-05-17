package com.flabedu.small.small.data;

import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
public class Orders {
    long orderId;
    long memberId;
    long totalPrice;
    String status = "S";
    Date ordersDate;
    Date modifiedDate;

    public Orders(long memberId, long totalPrice, String status, Date ordersDate, Date modifiedDate) {
        this(0, memberId,totalPrice,status,ordersDate,modifiedDate);
    }

    public Orders(long orderId, long memberId, long totalPrice, String status, Date ordersDate, Date modifiedDate) {
        this.memberId = memberId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.ordersDate = ordersDate;
        this.modifiedDate = modifiedDate;
    }
}
