package com.flabedu.small.small.service;

import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.exception.ErrorCodes;
import com.flabedu.small.small.mapper.ItemMapper;
import com.flabedu.small.small.mapper.MemberMapper;
import com.flabedu.small.small.mapper.OrdersItemMapper;
import com.flabedu.small.small.mapper.OrdersMapper;
import com.flabedu.small.small.dao.ItemDAO;
import com.flabedu.small.small.dao.ItemDetailDAO;
import com.flabedu.small.small.dao.MemberDAO;
import com.flabedu.small.small.dao.enums.GenderEnum;
import com.flabedu.small.small.dao.enums.SizeEnum;
import com.flabedu.small.small.web.dto.request.ItemDetailRequestDTO;
import com.flabedu.small.small.web.dto.request.ItemRequestDTO;
import com.flabedu.small.small.web.dto.request.OrderRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

@SpringBootTest
public class ItemServiceTest  {

    @Autowired
    ItemService itemService;

    @MockBean
    ItemMapper itemMapper;

    @MockBean
    MemberMapper memberMapper;

    @MockBean
    OrdersItemMapper ordersItemMapper;

    @MockBean
    OrdersMapper ordersMapper;

    MemberDAO dummyMember = MemberDAO.builder()
            .id(1l)
            .userId("a")
            .password("b")
            .failCount(3)
            .memberType("c")
            .registrationDate(LocalDateTime.now())
            .modifiedDate(LocalDateTime.now())
            .build();
    OrderRequestDTO dummyDto = new OrderRequestDTO();
    ItemDAO dummyItem = ItemDAO.builder()
            .itemId(1l)
            .name("a")
            .engName("b")
            .gender(GenderEnum.COMMON)
            .price(BigDecimal.valueOf(2))
            .registUserid("d")
            .registDate(LocalDateTime.now())
            .modifiedUserid("d")
            .modifiedDate(LocalDateTime.now())
            .build();
    ItemDetailDAO dummyItemDetail = ItemDetailDAO.builder()
                    .itemDetailId(1l)
                    .itemId(2l)
                    .stock(5)
                    .size(SizeEnum.M)
                    .build();
    ItemDetailRequestDTO itemDetailDTO;
    ItemRequestDTO itemDTO;

    @BeforeEach
    public void prepareDummy(){
        dummyDto = new OrderRequestDTO();
        var orderItem = new OrderRequestDTO.OrderItem();
        orderItem.setCount(3);
        dummyDto.getOrders().add(orderItem);

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
    @DisplayName("올바른 호출 시 ordersMapper의 insert가 호출되고 orderItemMapper의 saveOrderDetails가 호출된다.")
    public void correct(){
        when(memberMapper.findMemberById(any())).thenReturn(dummyMember);
        when(itemMapper.findItemById(anyLong())).thenReturn(dummyItem);
        when(itemMapper.findItemDetail(anyLong(), any()))
                .thenReturn(dummyItemDetail);

        itemService.purchaseItem(dummyDto, "user");

        verify(ordersMapper).insertOrders(any());
        verify(ordersItemMapper).saveOrderDetails(anyLong(), any());
    }

    @Test
    @DisplayName("사용자를 찾을 수 없을 경우 CANNOT_FIND_USER 에러 코드를 갖는 CustomException 을 발생시킨다.")
    public void noMember(){
        when(memberMapper.findMemberById(any())).thenReturn(null);

        CustomException ex = assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "no user"));
        assertEquals(ErrorCodes.CANNOT_FIND_USER, ex.getErrorCode());
    }

    @Test
    @DisplayName("상품을 찾을 수 없을 경우 CANNOT_FIND_ITEM 에러 코드를 갖는 CustomException 을 발생시킨다.")
    public void noItem(){
        when(memberMapper.findMemberById(any())).thenReturn(dummyMember);
        when(itemMapper.findItemById(anyLong())).thenReturn(null);

        CustomException ex = assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
        assertEquals(ErrorCodes.CANNOT_FIND_ITEM, ex.getErrorCode());
    }

    @Test
    @DisplayName("상품 사이즈를 찾을 수 없을 경우 CANNOT_FIND_ITEM_DETAIL 에러 코드를 갖는 CustomException 을 발생시킨다..")
    public void noItemDetail(){
        when(memberMapper.findMemberById(any())).thenReturn(dummyMember);
        when(itemMapper.findItemById(anyLong())).thenReturn(dummyItem);
        when(itemMapper.findItemDetail(anyLong(), any())).thenReturn(null);

        CustomException ex = assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
        assertEquals(ErrorCodes.CANNOT_FIND_ITEM_DETAIL, ex.getErrorCode());
    }

    @Test
    @DisplayName("상품의 재고가 부족할 경우 NoStockException 이 발생한다.")
    public void noItemStock(){
        ItemDetailDAO dummyItemDetail = ItemDetailDAO.builder()
                .itemDetailId(1l)
                .itemId(2l)
                .stock(2)
                .size(SizeEnum.M)
                .build();

        when(memberMapper.findMemberById(any())).thenReturn(dummyMember);
        when(itemMapper.findItemById(anyLong())).thenReturn(dummyItem);
        when(itemMapper.findItemDetail(anyLong(), any())).thenReturn(dummyItemDetail); // 재고를 2개로 설정

        // dummyDto의 주문 수량은 3개
        CustomException ex = assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
        assertEquals(ErrorCodes.NO_STOCK, ex.getErrorCode());
    }

    @Test
    @DisplayName("상품 등록 시 필요한 메소드가 모두 호출된다.")
    public void addItemSuccess(){
        itemService.addItem(itemDTO);

        verify(itemMapper).addItem(any(ItemDAO.class));
        verify(itemMapper).addItemCategory(any(), any());
        verify(itemMapper).addItemImage(any(),anyList());
        verify(itemMapper).addItemDetail(any(),anyList());
    }

}
