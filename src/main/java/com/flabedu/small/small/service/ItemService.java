package com.flabedu.small.small.service;

import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.mapper.ItemMapper;
import com.flabedu.small.small.web.dto.request.ItemRequestDTO;
import com.flabedu.small.small.web.dto.request.ItemDetailRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemMapper itemMapper;

    @Transactional
    public void addItem(ItemRequestDTO newItem) {
        Item item = Item.builder()
                .name(newItem.getItemName())
                .nameEng(newItem.getItemNameEn())
                .subCategory(newItem.getSubCategory())
                .gender(newItem.getGender().toString())
                .price(newItem.getPrice())
                .registerUserID("admin")
                .registerDate(LocalDateTime.now())
                .modifiedUserID("admin")
                .modifiedDate(LocalDateTime.now())
                .build();

        itemMapper.addItem(item);
        itemMapper.addItemCategory(item);
        itemMapper.addItemImage(item.getItemId(), newItem.getItemImages());
        itemMapper.addItemDetail(item.getItemId(),
                                newItem.getItemDetails().stream()
                                .map(ItemDetailRequestDTO::convertToModel)
                                .collect(Collectors.toList()));
    }

}
