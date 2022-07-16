package com.flabedu.small.small.web.dto.response;

import com.flabedu.small.small.dao.CategoryInfo;
import com.flabedu.small.small.dao.StockAndSize;
import com.flabedu.small.small.dao.enums.GenderEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class SelectedItemResponseDTO {

    @NotBlank(message = "상품명을 입력해주세요.")
    private String itemNameKr;

    @Pattern(regexp = "^[^ㄱ-힣]*$", message = "상품 영문명에 한글을 입력할 수 없습니다.")
    private String itemNameEn;

    @NotNull(message = "성별을 선택해주세요.")
    private GenderEnum gender;

    @PositiveOrZero(message = "가격은 0 이상의 정수만 입력 가능합니다.")
    private BigDecimal price;

    @NotNull(message = "카테고리 정보가 없습니다.")
    private List<CategoryInfo> category;

    @Size(min = 1, max = 9, message = "상품의 이미지는 1~9개 까지 등록 가능합니다.")
    private List<String> itemImages;

    @Size(min = 1, message = "상품 사이즈 및 재고를 최소 1개 이상 입력해주세요.")
    private List<StockAndSize> stockAndSizes;

}
