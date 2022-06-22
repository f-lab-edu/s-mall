package com.flabedu.small.small.dao;

import com.flabedu.small.small.dao.enums.GenderEnum;
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
public class ItemDAO {
    private long itemId;
    private String name;
    private String engName;
    private GenderEnum gender;
    private BigDecimal price;
    private String registUserid;
    private LocalDateTime registDate;
    private String modifiedUserid;
    private LocalDateTime modifiedDate;
}
