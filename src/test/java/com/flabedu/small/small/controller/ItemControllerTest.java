package com.flabedu.small.small.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flabedu.small.small.model.GenderEnum;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.SizeEnum;
import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.controller.ItemController;
import com.flabedu.small.small.web.dto.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService itemService;

    private ItemDetail itemDetail;
    private ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        itemDetail = ItemDetail.builder()
                .size(SizeEnum.M).stock(100l).build();

        itemDTO = ItemDTO.builder()
                .itemNameKr("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(Arrays.asList("img1.png","img2.png"))
                .itemDetails(Arrays.asList(itemDetail))
                .build();
    }

    @Test
    @DisplayName("/items POST 상품 등록 실패")
    public void addItemInvalidArgument() throws Exception {
        ItemDTO inValidItemDTO = ItemDTO.builder()
                .itemNameKr("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(-20000))
                .itemImages(null)
                .itemDetails(Arrays.asList(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        ResultActions actions = mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                ).andExpect(
                        status().isBadRequest()
                ).andDo(print());
    }

    @Test
    @DisplayName("/items POST 상품 등록")
    public void addItemSuccess() throws Exception {
        String content = objectMapper.writeValueAsString(itemDTO);

        ResultActions actions = mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpect(
                status().isCreated()
        ).andDo(print());
    }

}
