package com.flabedu.small.small.web.dto.request;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class SelectedItemRequestDTO {
    @Positive(message = "상품 식별 아이디가 있어야 합니다.")
    private Long id;
}
