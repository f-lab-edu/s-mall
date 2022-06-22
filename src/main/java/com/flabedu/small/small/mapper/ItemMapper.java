package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.ItemDAO;
import com.flabedu.small.small.dao.ItemDetailDAO;
import com.flabedu.small.small.dao.enums.SizeEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {
    Long addItem(ItemDAO item);
    void addItemCategory(ItemDAO item, @Param("itemCategory") Long itemCategory);
    void addItemImage(@Param("itemId") Long itemId, @Param("itemImages") List<String> itemImages);
    void addItemDetail(@Param("itemId") Long itemId, @Param("itemDetails") List<ItemDetailDAO> itemDetails);
    List<ItemDAO> findItemAll();
    ItemDAO findItemById(long id);
    List<ItemDetailDAO> findItemDetailAll();
    ItemDetailDAO findItemDetail(@Param("itemId") long itemId, @Param("size") SizeEnum size);
    void setStock(@Param("detailId") long detailId, @Param("stock")long stock);
    ItemDetailDAO findItemDetailById(@Param("detailId") long detailId);
}
