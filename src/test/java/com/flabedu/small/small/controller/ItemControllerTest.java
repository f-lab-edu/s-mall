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
import org.springframework.test.web.servlet.MvcResult;
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
                .itemName("테스트 아이템")
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
    @DisplayName("not null 필드에 값이 전송되지 않아 상품 등록을 실패할 경우 모든 에러메세지가 응답 바디에 포함된다.")
    public void addItemNotNullFieldIsNull() throws Exception {
        ItemDTO inValidItemDTO = ItemDTO.builder()
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                ).andExpectAll(
                        status().isInternalServerError(),
                        jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","상품명을 입력해주세요.").exists(),
                        jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","성별을 선택해주세요.").exists(),
                        jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","가격을 입력해주세요.").exists(),
                        jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","카테고리를 선택해주세요.").exists(),
                        jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","소카테고리를 선택해주세요.").exists(),
                        jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","상품의 이미지는 최소 1개 이상 등록해주세요.").exists(),
                        jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","상품 사이즈 및 재고를 최소 1개 이상 입력해주세요.").exists()
                    ).andDo(print());
    }

    @Test
    @DisplayName("상품 영문명에 한글이 포함되어 상품 등록을 실패할 경우 에러메세지가 응답 바디에 포함된다.")
    public void addItemItemNameEnContainsKorean() throws Exception {
        ItemDTO inValidItemDTO = ItemDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item마")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(Arrays.asList("img1.png","img2.png"))
                .itemDetails(Arrays.asList(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","상품 영문명에 한글을 입력할 수 없습니다.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("가격을 음수로 입력하여 상품 등록을 실패할 경우 에러메세지가 응답 바디에 포함된다.")
    public void addItemPriceIsNegative() throws Exception {
        ItemDTO inValidItemDTO = ItemDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(-30000))
                .itemImages(Arrays.asList("img1.png","img2.png"))
                .itemDetails(Arrays.asList(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","가격은 0 이상의 정수만 입력 가능합니다.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품이미지를 10개 이상 등록하여 상품 등록을 실패할 경우 에러메세지가 응답 바디에 포함된다.")
    public void addItemItemImagesCountOverflow() throws Exception {
        ItemDTO inValidItemDTO = ItemDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(Arrays.asList("img1.png","img2.png","img3.png","img4.png","img5.png",
                        "img6.png","img7.png","img8.png","img9.png","img10.png"))
                .itemDetails(Arrays.asList(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","상품의 이미지는 1~9개 까지 등록 가능합니다.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 상세 정보의 재고수량을 입력하지 않거나 음수를 입력하여 상품 등록을 실패할 경우 에러메세지가 응답 바디에 포함된다.")
    public void addItemItemDetailIsNullOrNegative() throws Exception {
        ItemDetailDTO itemDetail1 = ItemDetailDTO.builder()
                .size(SizeEnum.S).stock(-30l).build();
        ItemDetailDTO itemDetail2 = ItemDetailDTO.builder()
                .size(SizeEnum.M).build();

        ItemDTO inValidItemDTO = ItemDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(Arrays.asList("img1.png","img2.png"))
                .itemDetails(Arrays.asList(itemDetail1,itemDetail2))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","사이즈 별 재고수량은 0 이상의 정수만 입력 가능합니다.").exists(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","사이즈 별 재고수량을 입력해주세요.").exists()
        ).andDo(print());
    }

}
