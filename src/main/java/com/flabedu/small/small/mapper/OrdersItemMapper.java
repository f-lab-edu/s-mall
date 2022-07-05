package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.OrdersItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersItemMapper {
    void saveOrderDetails(long ordersId, List<OrdersItem> items);

    List<OrdersItem> findOrderDetailById(long ordersId);
}
