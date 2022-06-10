package com.flabedu.small.small.model;

import com.flabedu.small.small.model.enums.GenderEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Item {
    private final long itemId;
    private final String name;
    private final String nameEng;
    private final String gender;
    private final BigDecimal price;
    private Long subCategory;
    private final String registerUserID;
    private final LocalDateTime registerDate;
    private final String modifiedUserID;
    private final LocalDateTime modifiedDate;
}
