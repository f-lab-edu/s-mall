package com.flabedu.small.small.model;

import com.flabedu.small.small.model.enums.SizeEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ItemDetail {
    private Long itemDetailId;
    private Long itemId;
    private long stock;
    private SizeEnum size;
}
