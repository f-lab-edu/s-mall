package com.flabedu.small.small.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemCategory {
    private Long itemCategoryId;
    private Long itemId;
    private Long categoryId;
    private String registUserId;
    private LocalDateTime registDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;
}
