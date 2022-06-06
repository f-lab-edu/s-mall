package com.flabedu.small.small.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    SUCCEED("S"), CANCEL("C");
    private final String status;

    public static OrderStatus findOrderStatus(String status){
        return Arrays.stream(OrderStatus.values())
                .filter(v->v.status.equals(status))
                .findFirst()
                .orElseThrow();
    }
}
