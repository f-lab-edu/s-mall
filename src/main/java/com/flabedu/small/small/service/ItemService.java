package com.flabedu.small.small.service;

import com.flabedu.small.small.data.Item;
import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.web.dto.request.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(propagation = Propagation.NEVER)
    public void addItem(ItemDTO newItem){
        Item item = Item.builder()
                        .name(newItem.getName())
                        .engName(newItem.getEngName())
                        .subCategory(newItem.getSubCategory())
                        .gender(newItem.getGender())
                        .price(newItem.getPrice())
                        .itemImages(newItem.getItemImages())
                        .itemDetails(newItem.getItemDetails())
                        .registUserId("admin")
                        .registDate(LocalDateTime.now())
                        .modifiedUserId("admin")
                        .modifiedDate(LocalDateTime.now())
                    .build();

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
