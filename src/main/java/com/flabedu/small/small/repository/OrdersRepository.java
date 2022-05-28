package com.flabedu.small.small.repository;

import com.flabedu.small.small.model.Orders;
import com.flabedu.small.small.model.OrdersItem;
import com.flabedu.small.small.mapper.OrdersItemMapper;
import com.flabedu.small.small.mapper.OrdersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersRepository {
    private final OrdersItemMapper ordersItemMapper;
    private final OrdersMapper ordersMapper;

    public Orders findOrdersById(long orderId){
        return ordersMapper.findOrdersById(orderId);
    }

    public void insertOrders(Orders orders){
        ordersMapper.insertOrders(orders);
    }

    public void findOrdersItemById(long ordersId){
        ordersItemMapper.findOrderDetailById(ordersId);
    }
    public void saveOrdersItems(long ordersId, List<OrdersItem> items){
        ordersItemMapper.saveOrderDetails(ordersId, items);
    }
}
