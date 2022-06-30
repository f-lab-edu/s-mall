package com.flabedu.small.small.dao;

import lombok.Getter;

@Getter
public class CategoryItemCountDao {
    private Long categoryId;
    private Long parentId;
    private String name;
    private long itemCount;
}
