package com.flabedu.small.small.service;

import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.exception.ErrorCodes;
import com.flabedu.small.small.mapper.*;
import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
                .size(SizeEnum.M.toString()).stock(100l).build();

        itemDTO = ItemRequestDTO.builder()
                .itemName("test1")
                .itemNameEn(null)
                .subCategory(3l)
                .gender(GenderEnum.C)
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
        verify(itemRepository).addItemCategory(any(Item.class));
        verify(itemRepository).addItemImage(any(),anyList());
        verify(itemRepository).addItemDetail(any(),anyList());
    }

}
