package com.application.eurder.controller;

import com.application.eurder.dto.CreateItemDTO;
import com.application.eurder.dto.ItemDTO;
import com.application.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    public ItemService itemService;
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
    @PostMapping("/item")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO create(@RequestBody CreateItemDTO newItem){
        return itemService.create(newItem);
    }
}