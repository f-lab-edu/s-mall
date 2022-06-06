package com.flabedu.small.small.service;

import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.exception.ErrorCodes;
import com.flabedu.small.small.mapper.*;
import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.Member;
import com.flabedu.small.small.web.dto.request.OrderRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemServiceTest  {

    @Autowired
    ItemService itemService;

    @MockBean
    ItemDetailMapper itemDetailMapper;

    @MockBean
    ItemMapper itemMapper;

    @MockBean
    MemberMapper memberMapper;

    @MockBean
    OrdersItemMapper ordersItemMapper;

    @MockBean
    OrdersMapper ordersMapper;

    Member dummyMember = new Member(1,"a", "b",3, "c", LocalDateTime.now(), LocalDateTime.now());
    OrderRequestDTO dummyDto = new OrderRequestDTO();
    Item dummyItem = new Item(1, "a","b","c", BigDecimal.valueOf(2),"d",null, "e", null);
    ItemDetail dummyItemDetail = new ItemDetail(1, 2, 5, "중");

    @BeforeEach
    public void prepareDummy(){
        dummyDto = new OrderRequestDTO();
        var orderItem = new OrderRequestDTO.OrderItem();
        orderItem.setCount(3);
        dummyDto.getOrders().add(orderItem);
    }

    @Test
    @DisplayName("올바른 호출 시 ordersMapper의 insert가 호출되고 orderItemMapper의 saveOrderDetails가 호출된다.")
    public void correct(){
        when(memberMapper.findMemberById(any())).thenReturn(dummyMember);
        when(itemMapper.findItemById(anyLong())).thenReturn(dummyItem);
        when(itemDetailMapper.findItemDetail(anyLong(), any()))
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
        when(itemDetailMapper.findItemDetail(anyLong(), any())).thenReturn(null);

        CustomException ex = assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
        assertEquals(ErrorCodes.CANNOT_FIND_ITEM_DETAIL, ex.getErrorCode());
    }

    @Test
    @DisplayName("상품의 재고가 부족할 경우 NoStockException 이 발생한다.")
    public void noItemStock(){
        when(memberMapper.findMemberById(any())).thenReturn(dummyMember);
        when(itemMapper.findItemById(anyLong())).thenReturn(dummyItem);
        when(itemDetailMapper.findItemDetail(anyLong(), any())).thenReturn(dummyItemDetail); // 재고를 2개로 설정

        // dummyDto의 주문 수량은 3개
        CustomException ex = assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
        assertEquals(ErrorCodes.NO_STOCK, ex.getErrorCode());
    }
}
