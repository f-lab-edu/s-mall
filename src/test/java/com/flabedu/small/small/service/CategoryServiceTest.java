package com.flabedu.small.small.service;

import com.flabedu.small.small.dao.CategoryItemCountDao;
import com.flabedu.small.small.mapper.CategoryMapper;
import com.flabedu.small.small.web.dto.response.CategoryResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @MockBean
    CategoryMapper categoryMapper;

    @Test
    @DisplayName("카테고리 별 상품 개수 조회 시 예상 값이 반환된다")
    public void findCategory(){
        List<CategoryItemCountDao> expected = List.of(
                CategoryItemCountDao.builder()
                        .categoryId(1l).parentId(null).name("상의").itemCount(14).build(),
                CategoryItemCountDao.builder()
                        .categoryId(2l).parentId(null).name("하의").itemCount(2).build()
        );

        List<CategoryResponseDTO> test = List.of(
                new CategoryResponseDTO(1l,null,"상의",14),
                new CategoryResponseDTO(2l,null,"하의",2)
        );

        when(categoryMapper.findCategory()).thenReturn(expected);
        List<CategoryResponseDTO> actual = categoryService.findCategory();

        assertThat(actual.get(0).getCategoryId()).isEqualTo(expected.get(0).getCategoryId());
        assertThat(actual.get(0).getParentCategoryId()).isEqualTo(expected.get(0).getParentId());
        assertThat(actual.get(0).getCategoryName()).isEqualTo(expected.get(0).getName());
        assertThat(actual.get(0).getCategoryItemCount()).isEqualTo(expected.get(0).getItemCount());
        assertThat(actual.get(1).getCategoryId()).isEqualTo(expected.get(1).getCategoryId());
        assertThat(actual.get(1).getParentCategoryId()).isEqualTo(expected.get(1).getParentId());
        assertThat(actual.get(1).getCategoryName()).isEqualTo(expected.get(1).getName());
        assertThat(actual.get(1).getCategoryItemCount()).isEqualTo(expected.get(1).getItemCount());
    }
}
