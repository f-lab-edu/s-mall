package com.flabedu.small.small.repository;

import com.flabedu.small.small.web.dto.ItemDTO;
import com.flabedu.small.small.web.dto.ItemDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemRepository {
    long addItem(ItemDTO item);
    void addItemCategory(ItemDTO item);
    void addItemImage(long itemId, List<String> imageList);
    void addItemDetail(long itemId, List<ItemDetailDTO> itemDetailList);

}
