package com.flabedu.small.small.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Category {
    private Long categoryId;
    private Long parent;
    private String name;
    private String registUserId;
    private LocalDateTime registDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;
}
