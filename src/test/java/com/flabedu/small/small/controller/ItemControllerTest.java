package com.flabedu.small.small.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flabedu.small.small.model.enums.GenderEnum;
import com.flabedu.small.small.model.enums.SizeEnum;
import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.controller.ItemController;
import com.flabedu.small.small.web.dto.request.ItemRequestDTO;
import com.flabedu.small.small.web.dto.request.ItemDetailRequestDTO;
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
import java.util.List;

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

    private ItemDetailRequestDTO itemDetail;
    private ItemRequestDTO item;

    @BeforeEach
    void setUp() {
        itemDetail = ItemDetailRequestDTO.builder()
                .size(SizeEnum.M).stock(100l).build();

        item = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png", "img2.png"))
                .itemDetails(List.of(itemDetail))
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
    @DisplayName("상품 등록 실패 - 상품명 null")
    public void addItemFailItemNameIsNull() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","상품명을 입력해주세요.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 등록 실패 - 카테고리 null")
    public void addItemFailCategoryIsNull() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","카테고리를 선택해주세요.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 등록 실패 - 소카테고리 null")
    public void addItemFailSubCategoryIsNull() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","소카테고리를 선택해주세요.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 등록 실패 - 성별 null")
    public void addItemFailGenderIsNull() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(1l)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","성별을 선택해주세요.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 등록 실패 - 가격 null")
    public void addItemFailPriceIsNull() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(1l)
                .gender(GenderEnum.C)
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","가격을 입력해주세요.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 등록 실패 - 상품이미지 null")
    public void addItemFailItemImagesIsNull() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(1l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemDetails(List.of(itemDetail))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","상품의 이미지는 최소 1개 이상 등록해주세요.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 등록 실패 - 상품 상세 정보 null")
    public void addItemFailItemDetailsIsNull() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(1l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","상품 사이즈 및 재고를 최소 1개 이상 입력해주세요.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 등록 실패 - 상품영문명에 한글 포함")
    public void addItemItemNameEnContainsKorean() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item마")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail))
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
    @DisplayName("상품 등록 실패 - 가격에 음수 입력")
    public void addItemPriceIsNegative() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(-30000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail))
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
    @DisplayName("상품 등록 실패 - 상품 이미지 10개 이상 등록")
    public void addItemItemImagesCountOverflow() throws Exception {
        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png","img3.png","img4.png","img5.png",
                        "img6.png","img7.png","img8.png","img9.png","img10.png"))
                .itemDetails(List.of(itemDetail))
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
    @DisplayName("상품 등록 실패 - 상품 상세 정보의 재고수량 null")
    public void addItemItemDetailIsNull() throws Exception {
        ItemDetailRequestDTO itemDetail1 =  ItemDetailRequestDTO.builder()
                .size(SizeEnum.M).build();

        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail1))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","사이즈 별 재고수량을 입력해주세요.").exists()
        ).andDo(print());
    }

    @Test
    @DisplayName("상품 등록 실패 - 상품 상세 정보의 재고수량에 음수 입력")
    public void addItemItemDetailIsNegative() throws Exception {
        ItemDetailRequestDTO itemDetail1 = ItemDetailRequestDTO.builder()
                .size(SizeEnum.S).stock(-30l).build();

        ItemRequestDTO inValidItemDTO = ItemRequestDTO.builder()
                .itemName("테스트 아이템")
                .itemNameEn("test item")
                .category(1l)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(List.of("img1.png","img2.png"))
                .itemDetails(List.of(itemDetail1))
                .build();

        String content = objectMapper.writeValueAsString(inValidItemDTO);

        this.mockMvc.perform(
                post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpectAll(
                status().isInternalServerError(),
                jsonPath("$..error[?(@.fieldErrorMessages == '%s')]","사이즈 별 재고수량은 0 이상의 정수만 입력 가능합니다.").exists()
        ).andDo(print());
    }
}
