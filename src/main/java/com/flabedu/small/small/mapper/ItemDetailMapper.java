package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.ItemDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemDetailMapper {
    List<ItemDetail> list();

    ItemDetail findItemDetail(@Param("itemId") long itemId, @Param("size")String size);

    void setStock(@Param("detailId") long detailId, @Param("stock")long stock);

    ItemDetail findItemDetailById(@Param("detailId") long detailId);
}
