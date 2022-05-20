package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersMapper {
    void insertOrders(Orders orders);
    Orders getOrders(long orderId);
}
