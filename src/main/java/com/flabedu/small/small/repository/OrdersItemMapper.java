package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.OrdersItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersItemMapper {
    void saveOrderDetail(long ordersId, List<OrdersItem> items);
    List<OrdersItem> getOrderDetail(long ordersId);
}
