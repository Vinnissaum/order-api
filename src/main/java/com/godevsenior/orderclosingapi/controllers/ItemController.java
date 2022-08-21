package com.godevsenior.orderclosingapi.controllers;

import com.godevsenior.orderclosingapi.entities.Item;
import com.godevsenior.orderclosingapi.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity<List<Item>>findAll() {
        List<Item> itemsList = service.findAll();

        return ResponseEntity.ok().body(itemsList);
    }
}
