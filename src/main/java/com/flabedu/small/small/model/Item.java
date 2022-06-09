package com.flabedu.small.small.model;

import com.flabedu.small.small.model.enums.GenderEnum;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Item {
    private Long itemId;
    private String name;
    private String engName;
    private GenderEnum gender;
    private BigDecimal price;
    private Long subCategory;
    private String registUserId;
    private LocalDateTime registDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;
}
