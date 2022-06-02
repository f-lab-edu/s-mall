package com.flabedu.small.small.service;

import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.exception.ErrorCodes;
import com.flabedu.small.small.mapper.*;
import com.flabedu.small.small.model.*;
import com.flabedu.small.small.model.enums.OrderStatus;
import com.flabedu.small.small.web.dto.request.OrderRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDetailMapper itemDetailMapper;
    private final ItemMapper itemMapper;
    private final MemberMapper memberMapper;
    private final OrdersItemMapper ordersItemMapper;
    private final OrdersMapper ordersMapper;

    @Transactional(rollbackFor = Exception.class)
    public void purchaseItem(OrderRequestDTO itemInfo, String userID){
        Member user = memberMapper.findMemberById(userID);
        if(user == null) throw new CustomException(ErrorCodes.CANNOT_FIND_USER);

        List<OrdersItem> ordersItems = createOrderItems(itemInfo.getOrders());
        saveOrders(user, ordersItems);
    }

    private void saveOrders(Member user, List<OrdersItem> ordersItems) {
        BigDecimal totalPrice = ordersItems.stream().map(OrdersItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        LocalDateTime createDate = LocalDateTime.now();
        Orders orders = new Orders(0,user.getId(), totalPrice, createDate, OrderStatus.SUCCEED, createDate);

        ordersMapper.insertOrders(orders);
        ordersItemMapper.saveOrderDetails(orders.getOrderId(), ordersItems);
    }

    private List<OrdersItem> createOrderItems(List<OrderRequestDTO.OrderItem> itemInfo)
    {
        List<OrdersItem> ordersItems = new LinkedList<>();

        itemInfo.forEach(ordersItem-> {
            Item item = itemMapper.findItemById(ordersItem.getItemId());
            ItemDetail detail = itemDetailMapper.findItemDetail(ordersItem.getItemId(), ordersItem.getSize());

            if(item == null) throw new CustomException(ErrorCodes.CANNOT_FIND_ITEM);
            if(detail == null) throw new CustomException(ErrorCodes.CANNOT_FIND_ITEM_DETAIL);
            if(detail.getStock() - ordersItem.getCount() < 0) throw new CustomException(ErrorCodes.NO_STOCK);

            itemDetailMapper.setStock(
                    detail.getItemDetailId(),
                    detail.getStock() - ordersItem.getCount()
            );

            ordersItems.add(new OrdersItem(0,
                    item.getItemId(),
                    detail.getItemDetailId(),
                    ordersItem.getCount(),
                    item.getPrice().multiply(BigDecimal.valueOf(ordersItem.getCount())
            )));
        });
        return ordersItems;
    }
}
