package com.flabedu.small.small.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class PurchaseDTO {
    List<OrdersDTO> orders = new LinkedList<>();
}
