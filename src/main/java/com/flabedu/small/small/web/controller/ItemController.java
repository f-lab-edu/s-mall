package com.flabedu.small.small.web.controller;

import com.flabedu.small.small.exception.ItemException;
import com.flabedu.small.small.exception.MemberException;
import com.flabedu.small.small.service.contract.ItemService;
import com.flabedu.small.small.web.dto.request.ItemsProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class ItemController {

    ItemService service;

    //todo DTO validation check
    //todo Error에 대한 재학습
    @ResponseBody
    @PostMapping("/items/product")
    public ResponseEntity<String> purchaseItem(@Valid @RequestBody ItemsProductDTO purchaseItem){
        try {
            service.purchaseItem(purchaseItem, "test_user");
        }catch (ItemException | MemberException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
