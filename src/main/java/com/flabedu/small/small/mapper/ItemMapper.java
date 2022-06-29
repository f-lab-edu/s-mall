package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.CategoryInfo;
import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.ItemImage;
import com.flabedu.small.small.model.enums.SizeEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {
    //Insert
    Long addItem(Item item);
    void addItemCategory(Item item, @Param("itemCategory") Long itemCategory);
    void addItemImage(@Param("itemId") Long itemId, @Param("itemImages") List<String> itemImages);
    void addItemDetail(@Param("itemId") Long itemId, @Param("itemDetails") List<ItemDetail> itemDetails);

    //Update
    void setStock(@Param("detailId") long detailId, @Param("stock")long stock);

    //Get
    List<Item> findItemAll();
    Item findItemById(long id);
    List<ItemDetail> findItemDetailAll();
    ItemDetail findItemDetail(@Param("itemId") long itemId, @Param("size") SizeEnum size);
    List<ItemDetail> findItemDetailByItemId(@Param("itemId") long itemId);
    List<ItemImage> findItemImagesByItemId(@Param("itemId") long itemId);
    List<String> findItemImagesNameByItemId(@Param("itemId") long itemId);
    ItemDetail findItemDetailById(@Param("detailId") long detailId);
    List<CategoryInfo> getCategoryInfo(@Param("itemId") long itemId);
}
