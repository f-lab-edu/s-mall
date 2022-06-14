package com.flabedu.small.small.service;

import com.flabedu.small.small.mapper.ItemMapper;
import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.enums.GenderEnum;
import com.flabedu.small.small.model.enums.SizeEnum;
import com.flabedu.small.small.web.dto.request.ItemDetailRequestDTO;
import com.flabedu.small.small.web.dto.request.ItemRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemMapper itemRepository;

    @InjectMocks
    private ItemService itemService;

    private ItemDetailRequestDTO itemDetailDTO;
    private ItemRequestDTO itemDTO;

    @BeforeEach
    void setUp() {
        itemDetailDTO = ItemDetailRequestDTO.builder()
                .size(SizeEnum.M).stock(100l).build();

        itemDTO = ItemRequestDTO.builder()
                .itemName("test1")
                .itemNameEn(null)
                .subCategory(3l)
                .gender(GenderEnum.COMMON)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetailDTO))
                .build();
    }

    @Test
    @DisplayName("상품 등록 시 필요한 메소드가 모두 호출된다.")
    public void addItemSuccess(){
        itemService.addItem(itemDTO);

        verify(itemRepository).addItem(any(Item.class));
        verify(itemRepository).addItemCategory(any(), any());
        verify(itemRepository).addItemImage(any(),anyList());
        verify(itemRepository).addItemDetail(any(),anyList());
    }

}
