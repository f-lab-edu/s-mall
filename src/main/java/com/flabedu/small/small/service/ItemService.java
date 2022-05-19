package com.flabedu.small.small.service;

import com.flabedu.small.small.data.*;
import com.flabedu.small.small.web.dto.request.OrderItemDTO;
import com.flabedu.small.small.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Service
@AllArgsConstructor
public class ItemService {

    MemberMapper memberMapper;
    ItemMapper itemMapper;
    ItemDetailMapper itemDetailMapper;
    OrdersMapper ordersMapper;
    OrdersItemMapper ordersItemMapper;

    @Transactional
    public void purchaseItem(List<OrderItemDTO> itemInfo, String userID){
        Member user = memberMapper.getMember(userID);
        List<OrdersItem> ordersItems = createOrderItems(user, itemInfo);
        ordersItems.forEach(
                v-> System.out.println(v.getItemId())
        );
        saveOrders(user, ordersItems);
    }

    private void saveOrders(Member user, List<OrdersItem> ordersItems) {
        long totalPrice = ordersItems.stream().mapToLong(OrdersItem::getPrice).sum();
        LocalDateTime createDate = LocalDateTime.now();
        Orders orders = new Orders(user.getId(), totalPrice, Orders.OrderStatus.SUCCEED, createDate, createDate);
        ordersMapper.insertOrders(orders);
        long ordersId = orders.getOrderId();

        //todo : mybatis에서 루프 사용으로 변경
        ordersItems.forEach(item -> {
            item.setOrdersId(ordersId);
            ordersItemMapper.saveOrderDetail(item);
        });
    }


    private List<OrdersItem> createOrderItems(Member user, List<OrderItemDTO> itemInfo) {
        List<OrdersItem> ordersItems = new LinkedList<>();
        if(user == null) throw new IllegalArgumentException("로그인 되지 않음");

        itemInfo.forEach(ordersItem->{
            Item item = itemMapper.getItem(ordersItem.getItemId());
            ItemDetail detail = itemDetailMapper.getItemDetail(ordersItem.getItemId(), ordersItem.getSize());

            if(item == null) throw new IllegalArgumentException("존재하지 않은 Item");
            if(detail == null) throw new IllegalArgumentException("존재하지 않은 아이템 사이즈");
            if(detail.getStock() <= 0) throw new IllegalStateException("재고 없음");

            itemDetailMapper.setStock(
                    detail.getItemDetailId(),
                    detail.getStock() - ordersItem.getCount()
                    );

            ordersItems.add(new OrdersItem(0,
                        item.getItemId(),
                        detail.getItemDetailId(),
                        ordersItem.getCount(),
                        item.getPrice() * ordersItem.getCount()
                    ));
        });
        return ordersItems;
    }


}
