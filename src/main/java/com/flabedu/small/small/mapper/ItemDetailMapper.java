package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.ItemDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemDetailMapper {
    List<ItemDetail> list();

    ItemDetail findItemDetail(@Param("itemId") long itemId, @Param("size")String size);

    void setStock(@Param("detailId") long detailId, @Param("stock")long stock);

    ItemDetail findItemDetailById(@Param("detailId") long detailId);
}
