package com.flabedu.small.small.mapper;

import com.flabedu.small.small.domain.OrdersItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersItemMapper {
    void saveOrderDetails(long ordersId, List<OrdersItem> items);

    List<OrdersItem> findOrderDetailById(long ordersId);
}
