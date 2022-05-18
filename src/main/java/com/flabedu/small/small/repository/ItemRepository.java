package com.flabedu.small.small.repository;

import com.flabedu.small.small.dto.ItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface ItemRepository {
    long addItem(ItemDTO item);
    void addItemCategory(ItemDTO item);
    void addItemImage(Map<String, Object> mapperMap);
    void addItemDetail(Map<String, Object> mapperMap);

}
