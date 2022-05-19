package com.flabedu.small.small.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
public class ItemDTO {

    private long itemId;

    @NotBlank(message = "상품을 입력해주세요.")
    private String name;

    private String engName;

    @NotNull(message = "성별을 선택해주세요.")
    private GenderEnum gender;

    @PositiveOrZero
    @NotNull(message = "상품의 가격을 입력해주세요.")
    private BigDecimal price;

    @NotNull(message = "카테고리를 선택해주세요.")
    private long category;

    @NotNull(message = "소카테고리를 선택해주세요.")
    private long subCategory;

    @Size(min = 1, message = "상품의 이미지는 최소 1개 이상 등록해주세요.")
    private List<String> itemImages;

    @Size(min = 1, message = "상품 사이즈에 대한 재고를 최소 1개 이상 입력해주세요.")
    private List<ItemDetailDTO> itemDetails;

    private String registUserId;
    private LocalDateTime registDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;

}
