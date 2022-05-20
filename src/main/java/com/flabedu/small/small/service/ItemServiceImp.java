package com.flabedu.small.small.service;

import com.flabedu.small.small.data.*;
import com.flabedu.small.small.exception.*;
import com.flabedu.small.small.service.contract.ItemService;
import com.flabedu.small.small.web.dto.request.ItemsProductDTO;
import com.flabedu.small.small.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Service
@AllArgsConstructor
public class ItemServiceImp implements ItemService {

    MemberMapper memberMapper;
    ItemMapper itemMapper;
    ItemDetailMapper itemDetailMapper;
    OrdersMapper ordersMapper;
    OrdersItemMapper ordersItemMapper;

    @Transactional
    public void purchaseItem(ItemsProductDTO itemInfo, String userID) throws ItemException, MemberException{
        Member user = memberMapper.getMember(userID);
        List<OrdersItem> ordersItems = createOrderItems(user, itemInfo.getOrders());
        ordersItems.forEach(
                v-> System.out.println(v.getItemId())
        );
        saveOrders(user, ordersItems);
    }

    private void saveOrders(Member user, List<OrdersItem> ordersItems) {
        long totalPrice = ordersItems.stream().mapToLong(OrdersItem::getPrice).sum();
        LocalDateTime createDate = LocalDateTime.now();
        Orders orders = new Orders(0,user.getId(), totalPrice, createDate, Orders.OrderStatus.SUCCEED, createDate);
        ordersMapper.insertOrders(orders);
        long ordersId = orders.getOrderId();

        ordersItemMapper.saveOrderDetail(ordersId, ordersItems);
        ordersItemMapper.saveOrderDetail(ordersId, ordersItems);
    }


    private List<OrdersItem> createOrderItems(Member user, List<ItemsProductDTO.OrderItem> itemInfo)
    throws ItemException, MemberException
    {
        List<OrdersItem> ordersItems = new LinkedList<>();
        if(user == null) throw new CannotFindMemberException("로그인 되지 않음");

        itemInfo.forEach(ordersItem-> {
            Item item = itemMapper.getItem(ordersItem.getItemId());
            ItemDetail detail = itemDetailMapper.getItemDetail(ordersItem.getItemId(), ordersItem.getSize());

            if(item == null) throw new CannotFindItemException("존재하지 않은 Item");
            if(detail == null) throw new CannotFindItemDetailException("존재하지 않은 아이템 사이즈");
            if(detail.getStock() <= 0) throw new NoStockException("재고 없음");

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
