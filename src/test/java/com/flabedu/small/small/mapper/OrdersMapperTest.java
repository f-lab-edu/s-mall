package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.Orders;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrdersMapperTest {

    @Autowired
    OrdersMapper ordersMapper;

    @Test
    public void insertOrders_Get_OrderID_Test(){
        int inputOrderId = -1;
        var curTime =LocalDateTime.now();
        var orders = new Orders(inputOrderId, 2, 300,  curTime,Orders.OrderStatus.SUCCEED, curTime);

        ordersMapper.insertOrders(orders);

        assertNotEquals(inputOrderId, orders.getOrderId());
    }

    @Test
    public void insertOrders_Insert_Row_Test(){
        var curTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        System.out.println(curTime);
        var orders = new Orders(0, 2, 300,  curTime, Orders.OrderStatus.SUCCEED,curTime);

        ordersMapper.insertOrders(orders);
        var readOrders = ordersMapper.findOrdersById(orders.getOrderId());

        assertEquals(orders, readOrders);
    }
}
