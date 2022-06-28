package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.OrdersItemDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrdersItemMapperTest {

    @Autowired
    OrdersItemMapper ordersItemMapper;

    @Test
    @DisplayName("상세 주문 id로 상세 주문 아이템을 찾는다. 상세 주문 id가 동일하다.")
    public void getOrderDetailTest(){
        var id = 1;
        var item = ordersItemMapper.findOrderDetailById(id);
        Assertions.assertEquals(item.get(0).getOrdersId(), id);
    }

    @Test
    @DisplayName("2개의 상세 주문 아이템을 저장한 후 읽은 데이터가 동일한지 확인한다.")
    public void saveOrderDetailTest(){
        List<OrdersItemDao> inputList = new ArrayList<>();
        var ordersId = 1l;
        var ordersItem1 = OrdersItemDao.builder()
                .ordersId(ordersId)
                .itemId(10l)
                .itemDetailId(4l)
                .ordersItemCount(1)
                .price(BigDecimal.valueOf(5000))
                .build();
        var ordersItem2 = OrdersItemDao.builder()
                .ordersId(ordersId)
                .itemId(10l)
                .itemDetailId(5l)
                .ordersItemCount(2)
                .price(BigDecimal.valueOf(6000))
                .build();

        inputList.add(ordersItem1);
        inputList.add(ordersItem2);

        ordersItemMapper.saveOrderDetails(ordersId, inputList);
        var confirmList = ordersItemMapper.findOrderDetailById(ordersId);
        int confirmListSize = confirmList.size();

        Assertions.assertEquals(confirmList.get(confirmListSize-2), ordersItem1);
        Assertions.assertEquals(confirmList.get(confirmListSize-1), ordersItem2);
    }
}
