package com.flabedu.small.small.repository;

import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.mapper.ItemDetailMapper;
import com.flabedu.small.small.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final ItemDetailMapper itemDetailMapper;
    private final ItemMapper itemMapper;

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
