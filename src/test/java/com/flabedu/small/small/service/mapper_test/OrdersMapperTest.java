package com.flabedu.small.small.service.mapper_test;

import com.flabedu.small.small.data.Orders;
import com.flabedu.small.small.repository.ItemDetailMapper;
import com.flabedu.small.small.repository.ItemMapper;
import com.flabedu.small.small.repository.OrdersMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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
        var curTime = LocalDateTime.now();
        var orders = new Orders(0, 2, 300,  curTime, Orders.OrderStatus.SUCCEED,curTime);

        ordersMapper.insertOrders(orders);
        var readOrders = ordersMapper.getOrders(orders.getOrderId());

        assertEquals(readOrders, orders);
    }
}
