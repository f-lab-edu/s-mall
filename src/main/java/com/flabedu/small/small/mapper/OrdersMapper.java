package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {
    void insertOrders(Orders orders);

    Orders findOrdersById(long orderId);
}
