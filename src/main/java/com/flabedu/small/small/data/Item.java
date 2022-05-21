package com.flabedu.small.small.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
public class Item {

    private long itemId;
    private String name;
    private String engName;
    private GenderEnum gender;
    private BigDecimal price;
    private long subCategory;
    private List<String> itemImages;
    private List<ItemDetail> itemDetails;
    private String registUserId;
    private LocalDateTime registDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;

}
