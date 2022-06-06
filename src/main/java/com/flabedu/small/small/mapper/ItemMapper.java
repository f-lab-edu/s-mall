package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {
    List<Item> list();

    Item findItemById(long id);
}
