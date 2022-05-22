package com.flabedu.small.small.web.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ItemsProductDTO {
    @NotNull
    @NonNull
    @NotEmpty(message = "orders must not be empty")
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
        @NotEmpty
        String size;
    }
}
