package com.flabedu.small.small.service;

import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.web.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void addItem(ItemDTO item){
        // item.setMyUserId(); //세션 UserID 가져와야할듯?
        long itemId = itemRepository.addItem(item);

//        itemRepository.addItemCategory(item);
//        itemRepository.addItemImage(item.getItemId(), item.getItemImages());
//        itemRepository.addItemDetail(item.getItemId(), item.getItemDetails());
        System.out.println(item.getItemId());
        System.out.println(itemId);
    }
}
