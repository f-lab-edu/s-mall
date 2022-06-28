package com.flabedu.small.small.dao;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemCategoryDao {
    private Long itemCategoryId;
    private Long itemId;
    private Long categoryId;
    private String registUserId;
    private LocalDateTime registDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;
}
