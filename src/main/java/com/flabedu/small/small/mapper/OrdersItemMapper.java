package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.OrdersItemDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersItemMapper {
    void saveOrderDetails(long ordersId, List<OrdersItemDao> items);

    List<OrdersItemDao> findOrderDetailById(long ordersId);
}
