package com.flabedu.small.small.service.mapper_test;

import com.flabedu.small.small.data.OrdersItem;
import com.flabedu.small.small.repository.ItemDetailMapper;
import com.flabedu.small.small.repository.ItemMapper;
import com.flabedu.small.small.repository.OrdersItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrdersItemMapperTest {

    @Autowired
    OrdersItemMapper ordersItemMapper;

    @Test
    public void getOrderDetailTest(){
        var id = 1;
        var item = ordersItemMapper.getOrderDetail(id);
        Assertions.assertEquals(item.get(0).getOrdersId(), id);
    }

    @Test
    public void saveOrderDetailTest(){
        List<OrdersItem> inputList = new ArrayList<>();
        var ordersId = 1;
        var ordersItem1 =new OrdersItem(ordersId, 2, 2, 1, 5000);
        var ordersItem2 =new OrdersItem(ordersId, 2, 3, 2, 6000);
        inputList.add(ordersItem1);
        inputList.add(ordersItem2);

        ordersItemMapper.saveOrderDetail(ordersId, inputList);
        var confirmList = ordersItemMapper.getOrderDetail(ordersId);
        int confirmListSize = confirmList.size();

        Assertions.assertEquals(confirmList.get(confirmListSize-2), ordersItem1);
        Assertions.assertEquals(confirmList.get(confirmListSize-1), ordersItem2);
    }
}
