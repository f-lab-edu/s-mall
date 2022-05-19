package com.flabedu.small.small.web.controller;

import com.flabedu.small.small.web.dto.request.OrdersDTO;
import com.flabedu.small.small.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class ItemController {

    ItemService service;

    //todo DTO validate
    //todo Error에 대한 재학습
    @ResponseBody
    @PostMapping("/items/product")
    public ResponseEntity<String> purchaseItem( @RequestBody OrdersDTO purchaseItem){
        service.purchaseItem(purchaseItem.getOrders(), "test_user");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
