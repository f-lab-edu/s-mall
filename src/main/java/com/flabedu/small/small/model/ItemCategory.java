package com.flabedu.small.small.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ItemCategory {
    private Long itemCategoryId;
    private Long itemId;
    private Long categoryId;
    private String registUserId;
    private LocalDateTime registDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;
}
