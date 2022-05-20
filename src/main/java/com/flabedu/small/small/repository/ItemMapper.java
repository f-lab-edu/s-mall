package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.Item;
import com.flabedu.small.small.data.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("ItemRepo")
public interface ItemMapper {
    List<Item> list();

    Item getItem(long id);
}
