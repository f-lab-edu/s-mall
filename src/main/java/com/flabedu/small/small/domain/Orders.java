package com.flabedu.small.small.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Data
public class Orders {
    final long orderId;
    final long memberId;
    final long totalPrice;
    final LocalDateTime ordersDate;
    final OrderStatus status;
    final LocalDateTime modifiedDate;

    @Getter
    @RequiredArgsConstructor
    public enum OrderStatus{
        SUCCEED("S"), CANCEL("C");
        final String status;

        public static OrderStatus findOrderStatus(String status){
            return Arrays.stream(OrderStatus.values())
                    .filter(v->v.status.equals(status))
                    .findFirst()
                    .orElseThrow();
        }
    }
}
