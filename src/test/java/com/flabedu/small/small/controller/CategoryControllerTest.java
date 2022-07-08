package com.flabedu.small.small.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flabedu.small.small.service.CategoryService;
import com.flabedu.small.small.web.dto.response.CategoryResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CategoryService categoryService;

    @Test
    @DisplayName("카테고리 별 상품 개수 조회 시 예상한 값이 반환된다")
    public void findCategorySuccess() throws Exception {
        CategoryResponseDTO testDto0 = new CategoryResponseDTO(1l,null,"상의",14);
        CategoryResponseDTO testDto1 = new CategoryResponseDTO(2l,null,"하의",2);

        when(categoryService.findCategory()).thenReturn(List.of(testDto0, testDto1));

        ResultActions actions = mockMvc.perform(get("/category"))
                .andExpectAll(
                    status().isOk(),
                jsonPath("$[0].categoryId").value(testDto0.getCategoryId()),
                jsonPath("$[0].parentCategoryId").value(testDto0.getParentCategoryId()),
                jsonPath("$[0].categoryName").value(testDto0.getCategoryName()),
                jsonPath("$[0].categoryItemCount").value(testDto0.getCategoryItemCount()),
                jsonPath("$[1].categoryId").value(testDto1.getCategoryId()),
                jsonPath("$[1].parentCategoryId").value(testDto1.getParentCategoryId()),
                jsonPath("$[1].categoryName").value(testDto1.getCategoryName()),
                jsonPath("$[1].categoryItemCount").value(testDto1.getCategoryItemCount())
        ).andDo(print());
    }

}
