package com.flabedu.small.small.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@Builder
public class ItemDTO {

    private long itemId;

    @NotBlank(message = "상품을 입력해주세요.")
    private String name;

    private String nameEng;

    @NotNull(message = "성별을 선택해주세요.")
    private GenderEnum gender;

    @PositiveOrZero
    @NotNull(message = "상품의 가격을 입력해주세요.")
    private BigDecimal price;

    @NotNull(message = "카테고리를 선택해주세요.")
    private long categoryId;

    @NotNull(message = "소카테고리를 선택해주세요.")
    private long subCategoryId;

    @Size(min = 1)
    private List<String> itemImages;

    @Size(min = 1)
    private List<ItemDetailDTO> itemDetails;

    private String myUserId;

}
