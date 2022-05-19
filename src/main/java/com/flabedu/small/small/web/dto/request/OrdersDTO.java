package com.flabedu.small.small.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class OrdersDTO {
    @NotNull
    List<OrderItemDTO> orders = new LinkedList<>();
}
