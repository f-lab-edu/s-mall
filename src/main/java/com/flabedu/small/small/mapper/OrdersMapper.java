package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.OrdersDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrdersMapper {
    void insertOrders(OrdersDao orders);

    OrdersDao findOrdersById(long orderId);
}
