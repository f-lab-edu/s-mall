package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.ItemDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemDetailMapper {
    List<ItemDetail> list();

    ItemDetail getItemDetail(@Param("itemId") long itemId, @Param("size")String size);

    void setStock(@Param("detailId") long detailId, @Param("stock")long stock);

    ItemDetail getItemDetailById(@Param("detailId") long detailId);
}
