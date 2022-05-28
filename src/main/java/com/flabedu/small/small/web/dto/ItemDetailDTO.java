package com.flabedu.small.small.web.dto;

import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.enums.SizeEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ItemDetailDTO {
    private SizeEnum size;
    private Long stock;

    public ItemDetail convertToModel(){
        return ItemDetail.builder().size(size).stock(stock).build();
    }
}
