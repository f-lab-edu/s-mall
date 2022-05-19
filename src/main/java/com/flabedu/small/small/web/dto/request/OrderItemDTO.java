package com.flabedu.small.small.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderItemDTO {

    @NotNull
    long itemId;

    @NotNull
    @Positive
    long count;

    @NotNull
    String size;
}
