package com.flabedu.small.small.repository;

import com.flabedu.small.small.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface ItemRepository {
    long addItem(Item item);
    void addItemCategory(Item item);
    void addItemImage(Map<String, Object> mapperMap);
    void addItemDetail(Map<String, Object> mapperMap);

}
