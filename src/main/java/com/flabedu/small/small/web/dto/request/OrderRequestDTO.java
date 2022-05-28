package com.flabedu.small.small.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {

    @NotEmpty(message = "주문 아이템이 한개 이상이여야합니다.")
    private List<@Valid OrderItem> orders = new LinkedList<>();

    @Getter
    @Setter
    public static class OrderItem {

        @Positive(message = "아이템 아이디는 양수여야 합니다.")
        long itemId;

        @Positive(message = "주문 개수가 최소 한개 이상이 여야합니다.")
        long count;

        @NotEmpty(message = "사이즈가 입력되어야합니다.")
        String size;
    }
}
