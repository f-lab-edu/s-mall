package com.flabedu.small.small.web.controller;

import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.dto.request.ItemRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/items")
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Object> addItem(@RequestBody @Valid ItemRequestDTO newItem) {
        itemService.addItem(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getItems(@PathVariable("id") long itemId){
        if(itemId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(itemService.getItem(itemId));
    }
}
