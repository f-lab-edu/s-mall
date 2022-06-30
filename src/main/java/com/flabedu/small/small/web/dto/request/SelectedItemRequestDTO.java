package com.flabedu.small.small.web.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Getter
@Builder
public class SelectedItemRequestDTO {
    @Valid
    @Positive(message = "상품 식별 아이디가 있어야 합니다.")
    private Long id;
}
