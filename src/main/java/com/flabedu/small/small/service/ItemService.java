package com.flabedu.small.small.service;

import com.flabedu.small.small.data.*;
import com.flabedu.small.small.dto.PurchaseDTO;
import com.flabedu.small.small.repository.ItemDetailMapper;
import com.flabedu.small.small.repository.ItemMapper;
import com.flabedu.small.small.repository.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class ItemService {

    MemberMapper memberMapper;
    ItemMapper itemMapper;
    ItemDetailMapper itemDetailMapper;

    public ItemService(MemberMapper memberMapper, ItemMapper itemMapper, ItemDetailMapper itemDetailMapper) {
        this.memberMapper = memberMapper;
        this.itemMapper = itemMapper;
        this.itemDetailMapper = itemDetailMapper;
    }

    public void purchaseItem(PurchaseDTO itemInfo, long userID){
        //배열로 변경해야함
        Item item = itemMapper.getItem(itemInfo.getItemId());
        ItemDetail detail = itemDetailMapper.getItemDetail(itemInfo.getItemId(), itemInfo.getSize());

        if(item == null) throw new IllegalArgumentException("존재하지 않은 Item");

        if(detail == null) throw new IllegalArgumentException("존재하지 않은 아이템 사이즈");

        if(detail.getStock() <= 0) throw new IllegalStateException("재고 없음");

        Member user = memberMapper.getMember(userID);
        if(user == null) throw new IllegalArgumentException("사용자 없음");

        long time = new java.util.Date().getTime();
        Date createDate = new Date(time);
        Orders orders = new Orders(user.getId(), item.getPrice(), createDate, createDate);
        long ordersId = 0; //todo orders를  insert하면서 orderid를 가져와야함

        //루프로 변경해야함
        OrdersItem ordersItem = new OrdersItem(ordersId, item.getItemId(), detail.getItemDetailId(), itemInfo.getStock(), item.getPrice());

    }


}
