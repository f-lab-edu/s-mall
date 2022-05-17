package com.flabedu.small.small.web;

import com.flabedu.small.small.dto.PurchaseDTO;
import com.flabedu.small.small.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ItemController {


    ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping("/items/product")
    public void purchaseItem(@RequestBody PurchaseDTO purchaseItem, HttpSession session){
        service.purchaseItem(purchaseItem.getOrders(), session.getAttribute("UserID").toString());
    }
}
