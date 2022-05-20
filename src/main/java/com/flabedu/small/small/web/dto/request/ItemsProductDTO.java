package com.flabedu.small.small.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ItemsProductDTO {
    @NotNull
    List<OrderItem> orders = new LinkedList<>();

    @Getter
    @Setter
    public static class OrderItem {

        @NotNull
        long itemId;

        @NotNull
        @Positive
        long count;

        @NotNull
        String size;
    }
}
