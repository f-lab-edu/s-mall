package com.flabedu.small.small.service;

import com.flabedu.small.small.model.*;
import com.flabedu.small.small.exception.*;
import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.repository.MemberRepository;
import com.flabedu.small.small.repository.OrdersRepository;
import com.flabedu.small.small.web.dto.request.OrderRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;

    @Transactional(rollbackFor = Exception.class)
    public void purchaseItem(OrderRequestDTO itemInfo, String userID){
        Member user = memberRepository.findMemberById(userID);
        if(user == null) throw new CustomException(ErrorCodes.CANNOT_FIND_USER);

        List<OrdersItem> ordersItems = createOrderItems(itemInfo.getOrders());
        saveOrders(user, ordersItems);
    }

    private void saveOrders(Member user, List<OrdersItem> ordersItems) {
        long totalPrice = ordersItems.stream().mapToLong(OrdersItem::getPrice).sum();
        LocalDateTime createDate = LocalDateTime.now();
        Orders orders = new Orders(0,user.getId(), totalPrice, createDate, Orders.OrderStatus.SUCCEED, createDate);
        ordersRepository.insertOrders(orders);
        long ordersId = orders.getOrderId();

        ordersRepository.saveOrdersItems(ordersId, ordersItems);
    }


    private List<OrdersItem> createOrderItems(List<OrderRequestDTO.OrderItem> itemInfo)
    {
        List<OrdersItem> ordersItems = new LinkedList<>();

        itemInfo.forEach(ordersItem-> {
            Item item = itemRepository.findItemById(ordersItem.getItemId());
            ItemDetail detail = itemRepository.findItemDetail(ordersItem.getItemId(), ordersItem.getSize());

            if(item == null) throw new CustomException(ErrorCodes.CANNOT_FIND_ITEM);
            if(detail == null) throw new CustomException(ErrorCodes.CANNOT_FIND_ITEM_DETAIL);
            if(detail.getStock() - ordersItem.getCount() < 0) throw new CustomException(ErrorCodes.NO_STOCK);

            itemRepository.setItemStock(
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
