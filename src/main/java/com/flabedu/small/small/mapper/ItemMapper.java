package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.enums.SizeEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {
    Long addItem(Item item);
    void addItemCategory(Item item, @Param("itemCategory") Long itemCategory);
    void addItemImage(@Param("itemId") Long itemId, @Param("itemImages") List<String> itemImages);
    void addItemDetail(@Param("itemId") Long itemId, @Param("itemDetails") List<ItemDetail> itemDetails);
    List<Item> findItemAll();
    Item findItemById(long id);
    List<ItemDetail> findItemDetailAll();
    ItemDetail findItemDetail(@Param("itemId") long itemId, @Param("size") SizeEnum size);
    void setStock(@Param("detailId") long detailId, @Param("stock")long stock);
    ItemDetail findItemDetailById(@Param("detailId") long detailId);
}
