package com.flabedu.small.small.service;

import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.web.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void addItem(ItemDTO newItem) {
        Item item = Item.builder()
                .name(newItem.getItemNameKr())
                .engName(newItem.getItemNameEn())
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
        itemRepository.addItemImage(item.getItemId(), item.getItemImages());
        itemRepository.addItemDetail(item.getItemId(), item.getItemDetails());
    }

}
