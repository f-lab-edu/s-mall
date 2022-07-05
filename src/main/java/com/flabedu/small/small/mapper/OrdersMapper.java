package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrdersMapper {
    void insertOrders(Orders orders);

    Orders findOrdersById(long orderId);
}
