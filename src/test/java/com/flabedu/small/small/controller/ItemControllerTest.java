package com.flabedu.small.small.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flabedu.small.small.exception.*;
import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.dto.request.ItemsProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ItemControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ItemService itemService;

    @BeforeEach
    void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    @DisplayName("CANNOT_FIND_ITEM 예외를 발생하면 응답 코드 400과 에러 코드, 메시지를 전달한다.")
    void purchaseItemCannotFindItemException() throws Exception{
        exceptionTest(new CannotFindItemException(ItemErrorCodes.CANNOT_FIND_ITEM, "item error"));
    }

    @Test
    @DisplayName("CANNOT_FIND_ITEM_DETAIL 예외를 발생하면 응답 코드 400과 에러코드, 메시지를 전달한다.")
    void purchaseItemCannotFindItemDetailException() throws Exception{
        exceptionTest(new CannotFindItemDetailException(ItemErrorCodes.CANNOT_FIND_ITEM_DETAIL, "item_detail error"));
    }

    @Test
    @DisplayName("NO_STOCK 예외를 발생하면 응답 코드 400과 에러코드, 메시지를 전달한다.")
    void purchaseItemNoStockException() throws Exception{
        exceptionTest(new NoStockException(ItemErrorCodes.NO_STOCK, "no_stock error"));
    }

    private void exceptionTest(BaseException expectException) throws Exception {
        var dto = new ItemsProductDTO();
        dto.setOrders(List.of(new ItemsProductDTO.OrderItem()));

        doThrow(expectException)
                .when(itemService)
                .purchaseItem(any(), any());

        final ResultActions actions = mvc.perform(
                post("/items/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );

        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(ItemErrorCodes.CANNOT_FIND_ITEM.getCode())))
                .andExpect(jsonPath("$.message", equalTo("item error")))
                .andDo(print());
    }
}
