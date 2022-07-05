package com.flabedu.small.small.web.dto.request;

import com.flabedu.small.small.dao.enums.GenderEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@Builder
public class ItemRequestDTO {

    @NotBlank(message = "상품명을 입력해주세요.")
    private String itemName;

    @Pattern(regexp = "^[^ㄱ-힣]*$", message = "상품 영문명에 한글을 입력할 수 없습니다.")
    private String itemNameEn;

    @NotNull(message = "성별을 선택해주세요.")
    private GenderEnum gender;

    @NotNull(message = "가격을 입력해주세요.")
    @PositiveOrZero(message = "가격은 0 이상의 정수만 입력 가능합니다.")
    private BigDecimal price;

    @NotNull(message = "카테고리를 선택해주세요.")
    private Long category;

    @NotNull(message = "소카테고리를 선택해주세요.")
    private Long subCategory;

    @NotNull(message = "상품의 이미지는 최소 1개 이상 등록해주세요.")
    @Size(min = 1, max = 9, message = "상품의 이미지는 1~9개 까지 등록 가능합니다.")
    private List<String> itemImages;

    @Valid
    @NotNull(message = "상품 사이즈 및 재고를 최소 1개 이상 입력해주세요.")
    @Size(min = 1, message = "상품 사이즈 및 재고를 최소 1개 이상 입력해주세요.")
    private List<ItemDetailRequestDTO> itemDetails;

}
