package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.Item;
import com.flabedu.small.small.data.ItemDetail;
import com.flabedu.small.small.data.Orders;
import com.flabedu.small.small.data.OrdersItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ItemDetailMapper {
    @Select("SELECT * FROM item_detail")
    List<ItemDetail> list();

    @Select("SELECT * FROM item_detail WHERE ITEM_ID = #{itemId} AND SIZE = #{size}")
    ItemDetail getItemDetail(long itemId, String size);

    @Update("Update item_detail SET stock = #{stock} where item_detail_id = #{detailId}")
    void setStock(long detailId, long stock);

}
