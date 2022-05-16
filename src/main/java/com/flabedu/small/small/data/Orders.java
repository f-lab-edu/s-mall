package com.flabedu.small.small.data;

import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
public class Orders {
    long memberId;
    long totalPrice;
    Date ordersDate;
    Date modifiedDate;

    public Orders(long memberId, long totalPrice, Date ordersDate, Date modifiedDate) {
        this.memberId = memberId;
        this.totalPrice = totalPrice;
        this.ordersDate = ordersDate;
        this.modifiedDate = modifiedDate;
    }
}
