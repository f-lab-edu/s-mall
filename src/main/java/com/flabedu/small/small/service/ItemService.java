package com.flabedu.small.small.service;

import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void addItem(ItemDTO item){
        item.setRegistUserId("admin"); //세션에서 UserID 가져와야 하지않나?
        itemRepository.addItem(item);
        itemRepository.addItemCategory(item);

        Map<String, Object> mapperMap = new HashMap<>();
        mapperMap.put("itemId", item.getItemId());
        mapperMap.put("images", item.getItemImages());
        mapperMap.put("itemDetails", item.getItemDetails());

        itemRepository.addItemImage(mapperMap);
        itemRepository.addItemDetail(mapperMap);

    }
}
