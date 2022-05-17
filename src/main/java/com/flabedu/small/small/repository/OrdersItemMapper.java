package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.OrdersItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersItemMapper {

    @Insert("Insert into orders_item(" +
            "orders_id, item_id, item_detail_id, orders_item_count, price" +
            ") values(" +
            "#{ordersId}, #{itemId}, #{itemDetailId}, #{ordersItemCount}, #{price}" +
            ")")
    void saveOrderDetail(OrdersItem item);
}
