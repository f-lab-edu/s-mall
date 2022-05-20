package com.flabedu.small.small.data;

import lombok.*;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Orders {
    long orderId;
    long memberId;
    long totalPrice;
    LocalDateTime ordersDate;
    OrderStatus status;
    LocalDateTime modifiedDate;


    @Getter
    @AllArgsConstructor
    public enum OrderStatus{
        SUCCEED("S"), CANCEL("C");
        @NotNull
        String status;

        public static OrderStatus findOrderStatus(String status){
            return Arrays.stream(OrderStatus.values())
                    .filter(v->v.status.equals(status))
                    .findFirst()
                    .orElseThrow();
        }
    }
}
