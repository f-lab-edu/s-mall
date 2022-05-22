package com.flabedu.small.small.service;

import com.flabedu.small.small.domain.*;
import com.flabedu.small.small.exception.*;
import com.flabedu.small.small.mapper.*;
import com.flabedu.small.small.web.dto.request.ItemsProductDTO;
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

    @Transactional(rollbackFor = Exception.class)
    public void purchaseItem(ItemsProductDTO itemInfo, String userID) throws ItemException, MemberException{
        Member user = memberMapper.findMemberById(userID);
        if(user == null) throw new CannotFindMemberException(MemberErrorCodes.CANNOT_FIND_USER , "로그인 되지 않음");

        List<OrdersItem> ordersItems = createOrderItems(itemInfo.getOrders());
        saveOrders(user, ordersItems);
    }

    private void saveOrders(Member user, List<OrdersItem> ordersItems) {
        long totalPrice = ordersItems.stream().mapToLong(OrdersItem::getPrice).sum();
        LocalDateTime createDate = LocalDateTime.now();
        Orders orders = new Orders(0,user.getId(), totalPrice, createDate, Orders.OrderStatus.SUCCEED, createDate);
        ordersMapper.insertOrders(orders);
        long ordersId = orders.getOrderId();

        ordersItemMapper.saveOrderDetails(ordersId, ordersItems);
    }


    private List<OrdersItem> createOrderItems(List<ItemsProductDTO.OrderItem> itemInfo)
    throws ItemException, MemberException
    {
        List<OrdersItem> ordersItems = new LinkedList<>();

        itemInfo.forEach(ordersItem-> {
            Item item = itemMapper.findItemById(ordersItem.getItemId());
            ItemDetail detail = itemDetailMapper.findItemDetail(ordersItem.getItemId(), ordersItem.getSize());

            if(item == null) throw new CannotFindItemException(ItemErrorCodes.CANNOT_FIND_ITEM, "존재하지 않은 Item");
            if(detail == null) throw new CannotFindItemDetailException(ItemErrorCodes.CANNOT_FIND_ITEM_DETAIL, "존재하지 않은 아이템 사이즈");
            if(detail.getStock() <= 0) throw new NoStockException(ItemErrorCodes.NO_STOCK, "재고 없음");

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
