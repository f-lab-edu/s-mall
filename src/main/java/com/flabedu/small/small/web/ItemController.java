package com.flabedu.small.small.web;

import com.flabedu.small.small.dto.PurchaseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ItemController {

    @ResponseBody
    @PostMapping("/items/product")
    public void purchaseItem(@RequestBody PurchaseDTO purchaseItem, HttpSession session){

    }

}
