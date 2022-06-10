package com.flabedu.small.small.model;

import com.flabedu.small.small.model.enums.SizeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ItemDetail {
    private final long itemDetailId;
    private final long itemId;
    private final long stock;
    private final String size;
}
