package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.CategoryInfo;
import com.flabedu.small.small.dao.ItemDao;
import com.flabedu.small.small.dao.ItemDetailDao;
import com.flabedu.small.small.dao.ItemImageDao;
import com.flabedu.small.small.dao.enums.SizeEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {
    Long addItem(ItemDao item);
    void addItemCategory(ItemDao item, @Param("itemCategory") Long itemCategory);
    void addItemImage(@Param("itemId") Long itemId, @Param("itemImages") List<String> itemImages);
    void addItemDetail(@Param("itemId") Long itemId, @Param("itemDetails") List<ItemDetailDao> itemDetails);
    List<ItemDao> findItemAll();
    ItemDao findItemById(long id);
    List<ItemDetailDao> findItemDetailAll();
    ItemDetailDao findItemDetail(@Param("itemId") long itemId, @Param("size") SizeEnum size);
    void setStock(@Param("detailId") long detailId, @Param("stock")long stock);
    ItemDetailDao findItemDetailById(@Param("detailId") long detailId);
    List<ItemImageDao> findItemImagesByItemId(@Param("itemId") long itemId);
    List<String> findItemImagesNameByItemId(@Param("itemId") long itemId);
    List<CategoryInfo> getCategoryInfo(@Param("itemId") long itemId);
    List<ItemDetailDao> findItemDetailByItemId(@Param("itemId") long itemId);
}
