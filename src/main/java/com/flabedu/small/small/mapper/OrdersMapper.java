package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.OrdersDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrdersMapper {
    void insertOrders(OrdersDAO orders);

    OrdersDAO findOrdersById(long orderId);
}
