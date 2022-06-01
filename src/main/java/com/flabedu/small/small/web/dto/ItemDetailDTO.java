package com.flabedu.small.small.web.dto;

import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.enums.SizeEnum;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Builder
public class ItemDetailDTO {
    @NotNull(message = "상품의 사이즈를 선택해주세요.")
    private SizeEnum size;
    @NotNull(message = "사이즈 별 재고수량을 입력해주세요.")
    @PositiveOrZero(message = "사이즈 별 재고수량은 0 이상의 정수만 입력 가능합니다.")
    private Long stock;

    public ItemDetail convertToModel(){
        return ItemDetail.builder().size(size).stock(stock).build();
    }
}
