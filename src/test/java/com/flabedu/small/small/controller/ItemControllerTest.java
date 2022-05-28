package com.flabedu.small.small.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flabedu.small.small.model.enums.GenderEnum;
import com.flabedu.small.small.model.enums.SizeEnum;
import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.controller.ItemController;
import com.flabedu.small.small.web.dto.ItemDTO;
import com.flabedu.small.small.web.dto.ItemDetailDTO;
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

    private ItemDetailDTO itemDetail;
    private ItemDTO item;

    @BeforeEach
    void setUp() {
        itemDetail = ItemDetailDTO.builder()
                .size(SizeEnum.M).stock(100l).build();

        item = ItemDTO.builder()
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
    @DisplayName("상품 등록 성공")
    public void addItemSuccess() throws Exception {
        String content = objectMapper.writeValueAsString(item);

        ResultActions actions = mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpect(
                status().isCreated()
        ).andDo(print());
    }

    @Test
    @DisplayName("유효하지 않은 파라미터로 인한 상품 등록 실패")
    public void addItemFailWhenInvalidArgument() throws Exception {
        ItemDTO inValidItemDTO = ItemDTO.builder()
                .itemNameKr("유효하지 않은 파라미터")
                .itemNameEn("Fail When Invalid Argument")
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
    @DisplayName("부족한 파라미터 개수로 인한 상품 등록 실패")
    public void addItemFailWhenMissingArgument() throws Exception {
        ItemDTO inValidItemDTO = ItemDTO.builder()
                .itemNameKr("파라미터 개수 부족")
                .itemNameEn("Fail When Missing Argument")
                .category(1l)
                .price(new BigDecimal(20000))
                .itemImages(Arrays.asList("img1.png","img2.png"))
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
    @DisplayName("DB 에러로 인한 상품 등록 실패")
    public void addItemFailWhenDatabase() throws Exception {
        ItemDTO inValidItemDTO = ItemDTO.builder()
                .itemNameKr("데이터베이스 에러")
                .itemNameEn("Fail When Database Error")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(Arrays.asList("img1.png","img2.png"))
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

}
