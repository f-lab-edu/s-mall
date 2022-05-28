package com.flabedu.small.small.service;

import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.Member;
import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.repository.MemberRepository;
import com.flabedu.small.small.repository.OrdersRepository;
import com.flabedu.small.small.web.dto.request.OrderRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemServiceTest  {

    @Autowired
    ItemService itemService;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    MemberRepository memberRepository;

    @MockBean
    OrdersRepository ordersRepository;

    Member dummyMember = new Member(1,"a", "b",3, "c", LocalDateTime.now(), LocalDateTime.now());
    OrderRequestDTO dummyDto = new OrderRequestDTO();
    Item dummyItem = new Item(1, "a","b","c",2,"d",null, "e", null);

    @BeforeEach
    public void prepareDummy(){
        dummyDto = new OrderRequestDTO();
        var orderItem = new OrderRequestDTO.OrderItem();
        orderItem.setCount(3);
        dummyDto.setOrders(List.of(orderItem));
    }

    @Test
    @DisplayName("사용자를 찾을 수 없을 경우 CannotFindMemberException 이 발생한다.")
    public void noMember(){
        when(memberRepository.findMemberById(any())).thenReturn(null);

        Assertions.assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "no user"));
    }

    @Test
    @DisplayName("상품을 찾을 수 없을 경우 CannotFindItemException 이 발생한다.")
    public void noItem(){
        when(memberRepository.findMemberById(any())).thenReturn(dummyMember);
        when(itemRepository.findItemById(anyLong())).thenReturn(null);

        Assertions.assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
    }

    @Test
    @DisplayName("상품 사이즈를 찾을 수 없을 경우 CannotFindItemDetailException 이 발생한다.")
    public void noItemDetail(){
        when(memberRepository.findMemberById(any())).thenReturn(dummyMember);
        when(itemRepository.findItemById(anyLong())).thenReturn(dummyItem);
        when(itemRepository.findItemDetail(anyLong(), any())).thenReturn(null);

        Assertions.assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
    }

    @Test
    @DisplayName("상품의 재고가 부족할 경우 NoStockException 이 발생한다.")
    public void noItemStock(){
        when(memberRepository.findMemberById(any())).thenReturn(dummyMember);
        when(itemRepository.findItemById(anyLong())).thenReturn(dummyItem);
        when(itemRepository.findItemDetail(anyLong(), any())).
            thenReturn(new ItemDetail(1, 2, 2, "중")
            );

        Assertions.assertThrows(CustomException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
    }

}
