package com.flabedu.small.small.repository;

import com.flabedu.small.small.domain.Item;
import com.flabedu.small.small.domain.ItemDetail;
import com.flabedu.small.small.mapper.ItemDetailMapper;
import com.flabedu.small.small.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    final ItemDetailMapper itemDetailMapper;
    final ItemMapper itemMapper;

    public Item findItemById(long id){
        return itemMapper.findItemById(id);
    }

    public ItemDetail findItemDetailById(long id){
        return itemDetailMapper.findItemDetailById(id);
    }

    public ItemDetail findItemDetail(long itemId, String size){
        return itemDetailMapper.findItemDetail(itemId, size);
    }

    public void setItemStock(long itemId, long stock){
        itemDetailMapper.setStock(itemId, stock);
    }
}
