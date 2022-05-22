package com.flabedu.small.small.web.controller;

import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.dto.request.ItemsProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    final ItemService service;

    @ResponseBody
    @PostMapping("/product")
    public ResponseEntity<String> purchaseItem(@Valid @RequestBody ItemsProductDTO purchaseItem){
        service.purchaseItem(purchaseItem, "test_user");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
