package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.OrdersItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersItemMapper {
    void saveOrderDetail(OrdersItem item);
}
