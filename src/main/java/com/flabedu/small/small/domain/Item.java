package com.flabedu.small.small.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
public class Item {

    private Long itemId;
    private String name;
    private String engName;
    private GenderEnum gender;
    private BigDecimal price;
    private Long subCategory;
    private List<String> itemImages;
    private List<ItemDetail> itemDetails;
    private String registUserId;
    private LocalDateTime registDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;

}
