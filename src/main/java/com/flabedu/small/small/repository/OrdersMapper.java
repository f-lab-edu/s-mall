package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersMapper {
    @Insert("INSERT INTO orders " +
            "(member_id, total_price,  status,orders_date, modified_date) " +
            "values(#{memberId}, #{totalPrice}, #{status}, #{ordersDate}, #{modifiedDate})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void insertOrders(Orders orders);
}
