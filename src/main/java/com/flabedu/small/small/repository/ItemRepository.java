package com.flabedu.small.small.repository;

import com.flabedu.small.small.domain.Item;
import com.flabedu.small.small.domain.ItemDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ItemRepository {
    Long addItem(Item item);
    void addItemCategory(Item item);
    void addItemImage(@Param("itemId") Long itemId, @Param("itemImages") List<String> itemImages);
    void addItemDetail(@Param("itemId") Long itemId, @Param("itemDetails") List<ItemDetail> itemDetails);

}
