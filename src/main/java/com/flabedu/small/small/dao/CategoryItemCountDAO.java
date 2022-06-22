package com.flabedu.small.small.dao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryItemCountDAO {
    private Long categoryId;
    private Long parent;
    private String name;
    private long itemCount;
}
