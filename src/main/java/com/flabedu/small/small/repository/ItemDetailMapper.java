package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.ItemDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemDetailMapper {
    @Select("SELECT * FROM item_detail")
    List<ItemDetail> list();

    @Select("SELECT * FROM item_detail WHERE ITEM_ID = #{id} AND SIZE = #{size}")
    ItemDetail getItemDetail(long id, String size);

}
