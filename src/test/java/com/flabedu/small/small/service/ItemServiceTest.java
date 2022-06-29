package com.flabedu.small.small.service;

import com.flabedu.small.small.mapper.ItemMapper;
import com.flabedu.small.small.model.CategoryInfo;
import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.StockAndSize;
import com.flabedu.small.small.model.enums.GenderEnum;
import com.flabedu.small.small.model.enums.SizeEnum;
import com.flabedu.small.small.web.dto.request.ItemDetailRequestDTO;
import com.flabedu.small.small.web.dto.request.ItemRequestDTO;
import com.flabedu.small.small.web.dto.request.SelectedItemRequestDTO;
import com.flabedu.small.small.web.dto.response.SelectedItemResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemService itemService;

    private ItemDetailRequestDTO itemDetailDTO;
    private ItemRequestDTO itemDTO;

    private SelectedItemRequestDTO selectedItemRequestDTO;

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

        selectedItemRequestDTO = SelectedItemRequestDTO.builder().id(3L).build();
    }

    @Test
    @DisplayName("상품 등록 시 필요한 메소드가 모두 호출된다.")
    public void addItemSuccess(){
        itemService.addItem(itemDTO);

        verify(itemMapper).addItem(any(Item.class));
        verify(itemMapper).addItemCategory(any(), any());
        verify(itemMapper).addItemImage(any(),anyList());
        verify(itemMapper).addItemDetail(any(),anyList());
    }

    @Test
    @DisplayName("getItem 호출시 조회 관련 쿼리가 예상값과 동일한 SelectedItemResponseDTO를 반환한다.")
    public void getItemSuccess(){
        Item dummyItem = Item.builder().
                itemId(10).
                name("Test").
                engName("EngName").
                gender("C").
                price(BigDecimal.valueOf(100)).
                build();
        List<ItemDetail> dummyItemDetail = List.of(
                ItemDetail.builder().
                        itemId(10L).
                        itemDetailId(2L).
                        build());
        List<StockAndSize> dummyStockAndSize = dummyItemDetail.stream().map(v->
                StockAndSize.builder()
                        .stock(v.getStock())
                        .size(v.getSize())
                        .build()
                ).collect(Collectors.toList());
        List<String> itemImages = List.of("testImg1");
        List<CategoryInfo> categoryInfos = List.of(new CategoryInfo("상의", "티셔츠"));

        when(itemMapper.findItemById(anyLong())).thenReturn(dummyItem);
        when(itemMapper.findItemDetailByItemId(anyLong())).thenReturn(dummyItemDetail);
        when(itemMapper.findItemImagesNameByItemId(anyLong())).thenReturn(itemImages);
        when(itemMapper.getCategoryInfo(anyLong())).thenReturn(categoryInfos);

        SelectedItemResponseDTO result = itemService.getItem(selectedItemRequestDTO);

        Assertions.assertEquals(result.getItemNameKr(), dummyItem.getName());
        Assertions.assertEquals(result.getItemNameEn(), dummyItem.getEngName());
        Assertions.assertEquals(result.getGender(), dummyItem.getGender());
        Assertions.assertEquals(result.getPrice(), dummyItem.getPrice());
        Assertions.assertIterableEquals(result.getCategory(), categoryInfos);
        Assertions.assertIterableEquals(result.getItemImages(), itemImages);
        Assertions.assertIterableEquals(result.getStockAndSizes(), dummyStockAndSize);

    }

}
