package com.flabedu.small.small.service;

import com.flabedu.small.small.data.*;
import com.flabedu.small.small.dto.OrdersDTO;
import com.flabedu.small.small.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class ItemService {

    MemberMapper memberMapper;
    ItemMapper itemMapper;
    ItemDetailMapper itemDetailMapper;
    OrdersMapper ordersMapper;
    OrdersItemMapper ordersItemMapper;

    public ItemService(MemberMapper memberMapper, ItemMapper itemMapper, ItemDetailMapper itemDetailMapper, OrdersMapper ordersMapper, OrdersItemMapper ordersItemMapper) {
        this.memberMapper = memberMapper;
        this.itemMapper = itemMapper;
        this.itemDetailMapper = itemDetailMapper;
        this.ordersMapper = ordersMapper;
        this.ordersItemMapper = ordersItemMapper;
    }

    @Transactional
    public void purchaseItem(List<OrdersDTO> itemInfo, String userID){
        Member user = memberMapper.getMember(userID);
        List<OrdersItem> ordersItems = createOrderItems(user, itemInfo);
        ordersItems.forEach(
                v-> System.out.println(v.getItemId())
        );
        saveOrders(user, ordersItems);
    }

    private void saveOrders(Member user, List<OrdersItem> ordersItems) {
        long totalPrice = ordersItems.stream().mapToLong(OrdersItem::getPrice).sum();
        long time = new java.util.Date().getTime();
        Date createDate = new Date(time);
        Orders orders = new Orders(user.getId(), totalPrice, "S", createDate, createDate);
        ordersMapper.insertOrders(orders);
        long ordersId = orders.getOrderId();

        ordersItems.forEach(item -> {
            item.setOrdersId(ordersId);
            ordersItemMapper.saveOrderDetail(item);
        });
    }

    private List<OrdersItem> createOrderItems(Member user, List<OrdersDTO> itemInfo) {
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
                    detail.getStock() - ordersItem.getStock()
                    );

            ordersItems.add(new OrdersItem(0,
                        item.getItemId(),
                        detail.getItemDetailId(),
                        ordersItem.getStock(),
                        item.getPrice() * ordersItem.getStock()
                    ));
        });
        return ordersItems;
    }


}
