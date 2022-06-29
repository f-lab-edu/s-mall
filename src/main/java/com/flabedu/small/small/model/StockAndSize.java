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
public class StockAndSize {
    private final SizeEnum size;
    private final Long stock;
}
