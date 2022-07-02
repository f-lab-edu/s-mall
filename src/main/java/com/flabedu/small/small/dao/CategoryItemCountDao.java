package com.flabedu.small.small.dao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryItemCountDao {
    private Long categoryId;
    private Long parentId;
    private String name;
    private long itemCount;
}
