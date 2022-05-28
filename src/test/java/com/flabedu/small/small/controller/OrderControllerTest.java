package com.flabedu.small.small.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.exception.ErrorCodes;
import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.dto.request.OrderRequestDTO;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ItemService itemService;

    OrderRequestDTO testDto;

    @BeforeEach
    void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
        testDto = new OrderRequestDTO();

        var orderItem = new OrderRequestDTO.OrderItem();
        orderItem.setItemId(1);
        orderItem.setCount(3);
        orderItem.setSize("중");
        testDto.setOrders(List.of(orderItem));
    }

    @Test
    @DisplayName("정상 수행시 itemService 의 purchaseItem 메서드를 호출하고 응답코드 201을 전달한다.")
    void purchaseItem() throws Exception{

        final ResultActions actions = mvc.perform(
                post("/items/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDto))
        );

        verify(itemService).purchaseItem(any(), anyString());
        actions.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("CANNOT_FIND_ITEM 예외를 발생하면 응답 코드 400과 에러 코드, 메시지를 전달한다.")
    void purchaseItemCannotFindItemException() throws Exception{
        exceptionTest(new CustomException(ErrorCodes.CANNOT_FIND_ITEM));
    }

    @Test
    @DisplayName("CANNOT_FIND_ITEM_DETAIL 예외를 발생하면 응답 코드 400과 에러코드, 메시지를 전달한다.")
    void purchaseItemCannotFindItemDetailException() throws Exception{
        exceptionTest(new CustomException(ErrorCodes.CANNOT_FIND_ITEM_DETAIL));
    }

    @Test
    @DisplayName("NO_STOCK 예외를 발생하면 응답 코드 400과 에러코드, 메시지를 전달한다.")
    void purchaseItemNoStockException() throws Exception{
        exceptionTest(new CustomException(ErrorCodes.NO_STOCK));
    }

    @Test
    @DisplayName("DTO 값 예외.")
    void purchaseItemDTOException() throws Exception{
        var dto = new OrderRequestDTO();

        final ResultActions actions = mvc.perform(
                post("/items/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
        actions.andExpect(status().isBadRequest())
                .andDo(print());
    }


    private void exceptionTest(CustomException expectException) throws Exception {
        doThrow(expectException)
                .when(itemService)
                .purchaseItem(any(), any());

        final ResultActions actions = mvc.perform(
                post("/items/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDto))
        );

        actions.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(expectException.getErrorCode().getCode())))
                .andExpect(jsonPath("$.message", equalTo(expectException.getErrorCode().getMessage())));

    }
}
