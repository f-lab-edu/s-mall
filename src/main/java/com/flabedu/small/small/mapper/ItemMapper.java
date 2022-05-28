package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<Item> list();

    Item findItemById(long id);
}
