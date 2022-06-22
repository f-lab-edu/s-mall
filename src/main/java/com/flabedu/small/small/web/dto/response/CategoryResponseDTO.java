package com.flabedu.small.small.web.dto.response;

import com.flabedu.small.small.dao.CategoryItemCountDAO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryResponseDTO {
    private Long categoryId;
    private Long parentCategoryId;
    private String categoryName;
    private long categoryItemCount;

    public CategoryResponseDTO(CategoryItemCountDAO category) {
        this.categoryId = category.getCategoryId();
        this.parentCategoryId = category.getParent();
        this.categoryName = category.getName();
        this.categoryItemCount = category.getItemCount();
    }
}
