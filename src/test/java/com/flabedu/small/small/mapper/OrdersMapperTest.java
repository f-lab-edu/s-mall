package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.OrdersDao;
import com.flabedu.small.small.dao.enums.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.math.BigDecimal;
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
    @DisplayName("주문을 삽입하고 데이터 베이스에 삽입된 id가 삽입한 객체에 반영되었는지 확인한다.")
    public void insertOrders_Get_OrderID_Test(){
        int inputOrderId = -1;
        var curTime = LocalDateTime.now();
        var orders = OrdersDao.builder()
                .orderId(inputOrderId)
                .memberId(2)
                .totalPrice(BigDecimal.valueOf(300))
                .ordersDate(curTime)
                .status(OrderStatus.SUCCEED)
                .modifiedDate(curTime)
                .build();

        ordersMapper.insertOrders(orders);

        assertNotEquals(inputOrderId, orders.getOrderId());
    }

    @Test
    @DisplayName("주문을 삽입하고 삽입되었는지 확인한다.")
    public void insertOrders_Insert_Row_Test(){
        var curTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        var orders = OrdersDao.builder()
                .orderId(0l)
                .memberId(2)
                .totalPrice(BigDecimal.valueOf(300))
                .ordersDate(curTime)
                .status(OrderStatus.SUCCEED)
                .modifiedDate(curTime)
                .build();

        ordersMapper.insertOrders(orders);
        var readOrders = ordersMapper.findOrdersById(orders.getOrderId());

        assertEquals(orders, readOrders);
    }
}
