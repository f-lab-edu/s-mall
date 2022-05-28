package com.flabedu.small.small.web.controller;

import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.dto.request.OrderRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j
public class OrderController {

    private final ItemService service;

    @ResponseBody
    @PostMapping("/product")
    public ResponseEntity<String> orderItem(@Valid @RequestBody OrderRequestDTO purchaseItem, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(c-> log.error(c.toString()));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.purchaseItem(purchaseItem, "test_user");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
