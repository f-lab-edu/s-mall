package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.Item;
import com.flabedu.small.small.data.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Select("SELECT * FROM item")
    List<Item> list();

    @Select("SELECT * FROM item WHERE item_id = #{id}")
    Item getItem(long id);

}
