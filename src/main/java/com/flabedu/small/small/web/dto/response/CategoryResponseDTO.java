package com.flabedu.small.small.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryResponseDTO {
    private Long categoryId;
    private Long parentCategoryId;
    private String categoryName;
    private long categoryItemCount;
}
