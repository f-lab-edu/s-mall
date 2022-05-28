package com.flabedu.small.small.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Data
public class Orders {
    private final long orderId;
    private final long memberId;
    private final long totalPrice;
    private final LocalDateTime ordersDate;
    private final OrderStatus status;
    private final LocalDateTime modifiedDate;

    @Getter
    @RequiredArgsConstructor
    public enum OrderStatus{
        SUCCEED("S"), CANCEL("C");
        private final String status;

        public static OrderStatus findOrderStatus(String status){
            return Arrays.stream(OrderStatus.values())
                    .filter(v->v.status.equals(status))
                    .findFirst()
                    .orElseThrow();
        }
    }
}
