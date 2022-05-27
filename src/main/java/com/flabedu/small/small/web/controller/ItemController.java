package com.flabedu.small.small.web.controller;

import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/items")
@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Object> addItem(@RequestBody @Validated ItemDTO newItem) {
        itemService.addItem(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
