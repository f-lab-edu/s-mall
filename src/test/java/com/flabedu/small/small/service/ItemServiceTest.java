package com.flabedu.small.small.service;

import com.flabedu.small.small.domain.Item;
import com.flabedu.small.small.domain.ItemDetail;
import com.flabedu.small.small.domain.Member;
import com.flabedu.small.small.exception.CannotFindItemDetailException;
import com.flabedu.small.small.exception.CannotFindItemException;
import com.flabedu.small.small.exception.CannotFindMemberException;
import com.flabedu.small.small.exception.NoStockException;
import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.repository.MemberRepository;
import com.flabedu.small.small.repository.OrdersRepository;
import com.flabedu.small.small.web.dto.request.ItemsProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

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
    ItemsProductDTO dummyDto = new ItemsProductDTO();
    Item dummyItem = new Item(1, "a","b","c",2,"d",null, "e", null);

    @BeforeEach
    public void prepareDummy(){
        dummyDto = new ItemsProductDTO();
        var orderItem = new ItemsProductDTO.OrderItem();
        orderItem.setCount(3);
        dummyDto.setOrders(List.of(orderItem));
    }

    @Test
    @DisplayName("사용자를 찾을 수 없을 경우 CannotFindMemberException 이 발생한다.")
    public void noMember(){
        Mockito.when(memberRepository.findMemberById(any())).thenReturn(null);

        Assertions.assertThrows(CannotFindMemberException.class, ()->itemService.purchaseItem(dummyDto, "no user"));
    }

    @Test
    @DisplayName("상품을 찾을 수 없을 경우 CannotFindItemException 이 발생한다.")
    public void noItem(){
        Mockito.when(memberRepository.findMemberById(any())).thenReturn(dummyMember);
        Mockito.when(itemRepository.findItemById(anyLong())).thenReturn(null);

        Assertions.assertThrows(CannotFindItemException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
    }

    @Test
    @DisplayName("상품 사이즈를 찾을 수 없을 경우 CannotFindItemDetailException 이 발생한다.")
    public void noItemDetail(){
        Mockito.when(memberRepository.findMemberById(any())).thenReturn(dummyMember);
        Mockito.when(itemRepository.findItemById(anyLong())).thenReturn(dummyItem);
        Mockito.when(itemRepository.findItemDetail(anyLong(), any())).thenReturn(null);

        Assertions.assertThrows(CannotFindItemDetailException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
    }

    @Test
    @DisplayName("상품의 재고가 부족할 경우 NoStockException 이 발생한다.")
    public void noItemStock(){
        Mockito.when(memberRepository.findMemberById(any())).thenReturn(dummyMember);
        Mockito.when(itemRepository.findItemById(anyLong())).thenReturn(dummyItem);
        Mockito.when(
                itemRepository.findItemDetail(anyLong(), any())).
                thenReturn(new ItemDetail(1, 2, 2, "중")
                );

        Assertions.assertThrows(NoStockException.class, ()->itemService.purchaseItem(dummyDto, "test user"));
    }

}
