package com.flabedu.small.small.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class Item {

    private long itemId;

    @NotEmpty(message = "상품을 입력해주세요.")
    private String name;

    @Setter
    private String nameEng;

    @NotEmpty(message = "성별을 선택해주세요.")
    private GenderEnum gender;

    @NotEmpty(message = "상품의 가격을 입력해주세요.")
    private BigDecimal price;

    private long categoryId;
    private long subCategoryId;
    private List<String> itemImages;
    private List<ItemDetail> itemDetails;

    @Setter
    private String myUserId;

}
