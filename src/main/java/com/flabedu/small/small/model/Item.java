package com.flabedu.small.small.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Item {
    private long itemId;
    private String name;
    private String nameEng;
    private String gender;
    private BigDecimal price;
    private String registUserid;
    private LocalDateTime registDate;
    private String modifiedUserid;
    private LocalDateTime modifiedDate;
}
