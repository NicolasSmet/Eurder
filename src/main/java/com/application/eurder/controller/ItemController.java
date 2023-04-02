package com.application.eurder.controller;

import com.application.eurder.dto.CreateItemDTO;
import com.application.eurder.dto.ItemDTO;
import com.application.eurder.exceptions.FieldFormatNotValidException;
import com.application.eurder.security.Feature;
import com.application.eurder.service.ItemService;
import com.application.eurder.security.SecurityService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ItemController{
    public ItemService itemService;
    public SecurityService securityService;
    public ItemController(ItemService itemService, SecurityService securityService){

        this.itemService = itemService;
        this.securityService = securityService;
    }
    @PostMapping("/item")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO create(@RequestHeader String authorization, @RequestBody CreateItemDTO newItem){
        securityService.validateAuthorization(authorization, Feature.CREATE_ITEM);
        return itemService.create(newItem);
    }
    @ExceptionHandler(FieldFormatNotValidException.class)
    public void FieldFormatNotValidException(FieldFormatNotValidException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN,ex.getMessage());
    }
}