package com.flabedu.small.small.service;

import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.exception.ErrorCodes;
import com.flabedu.small.small.mapper.ItemMapper;
import com.flabedu.small.small.mapper.MemberMapper;
import com.flabedu.small.small.mapper.OrdersItemMapper;
import com.flabedu.small.small.mapper.OrdersMapper;
import com.flabedu.small.small.dao.*;
import com.flabedu.small.small.dao.enums.OrderStatus;
import com.flabedu.small.small.web.dto.request.ItemDetailRequestDTO;
import com.flabedu.small.small.web.dto.request.ItemRequestDTO;
import com.flabedu.small.small.web.dto.request.OrderRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;
    private final MemberMapper memberMapper;
    private final OrdersItemMapper ordersItemMapper;
    private final OrdersMapper ordersMapper;

    @Transactional(rollbackFor = Exception.class)
    public void purchaseItem(OrderRequestDTO itemInfo, String userID){
        MemberDAO user = memberMapper.findMemberById(userID);
        if(user == null) throw new CustomException(ErrorCodes.CANNOT_FIND_USER);

        List<OrdersItemDAO> ordersItems = createOrderItems(itemInfo.getOrders());
        saveOrders(user, ordersItems);
    }

    private void saveOrders(MemberDAO user, List<OrdersItemDAO> ordersItems) {
        BigDecimal totalPrice = ordersItems.stream().map(OrdersItemDAO::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        LocalDateTime createDate = LocalDateTime.now();
        OrdersDAO orders = OrdersDAO.builder()
                .orderId(0)
                .memberId(user.getId())
                .totalPrice(totalPrice)
                .ordersDate(createDate)
                .status(OrderStatus.SUCCEED)
                .modifiedDate(createDate)
                .build();

        ordersMapper.insertOrders(orders);
        ordersItemMapper.saveOrderDetails(orders.getOrderId(), ordersItems);
    }

    private List<OrdersItemDAO> createOrderItems(List<OrderRequestDTO.OrderItem> itemInfo)
    {
        List<OrdersItemDAO> ordersItems = new ArrayList<>();

        itemInfo.forEach(ordersItem-> {
            ItemDAO item = itemMapper.findItemById(ordersItem.getItemId());
            ItemDetailDAO detail = itemMapper.findItemDetail(ordersItem.getItemId(), ordersItem.getSize());

            if(item == null) throw new CustomException(ErrorCodes.CANNOT_FIND_ITEM);
            if(detail == null) throw new CustomException(ErrorCodes.CANNOT_FIND_ITEM_DETAIL);
            if(detail.getStock() - ordersItem.getCount() < 0) throw new CustomException(ErrorCodes.NO_STOCK);

            itemMapper.setStock(
                    detail.getItemDetailId(),
                    detail.getStock() - ordersItem.getCount()
            );

            ordersItems.add(OrdersItemDAO.builder()
                            .ordersId(0l)
                            .itemId(item.getItemId())
                            .itemDetailId(detail.getItemDetailId())
                            .ordersItemCount(ordersItem.getCount())
                            .price(item.getPrice().multiply(BigDecimal.valueOf(ordersItem.getCount())))
                            .build()
                    );
        });
        return ordersItems;
    }

    @Transactional
    public void addItem(ItemRequestDTO newItem) {
        ItemDAO item = ItemDAO.builder()
                .name(newItem.getItemName())
                .engName(newItem.getItemNameEn())
                .gender(newItem.getGender())
                .price(newItem.getPrice())
                .registUserid("admin")
                .registDate(LocalDateTime.now())
                .modifiedUserid("admin")
                .modifiedDate(LocalDateTime.now())
                .build();

        itemMapper.addItem(item);
        itemMapper.addItemCategory(item, newItem.getSubCategory());
        itemMapper.addItemImage(item.getItemId(), newItem.getItemImages());
        itemMapper.addItemDetail(item.getItemId(),
                newItem.getItemDetails().stream()
                        .map(ItemDetailRequestDTO::convertToModel)
                        .collect(Collectors.toList()));
    }

}
