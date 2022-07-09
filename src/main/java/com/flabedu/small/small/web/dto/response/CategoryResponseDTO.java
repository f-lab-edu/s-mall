package com.flabedu.small.small.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponseDTO {
    private Long categoryId;
    private Long parentCategoryId;
    private String categoryName;
    private long categoryItemCount;
}
