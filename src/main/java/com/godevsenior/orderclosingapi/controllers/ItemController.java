package com.godevsenior.orderclosingapi.controllers;

import com.godevsenior.orderclosingapi.dto.ItemDTO;
import com.godevsenior.orderclosingapi.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity<List<ItemDTO>>findAll() {
        List<ItemDTO> itemsList = service.findAll();

        return ResponseEntity.ok().body(itemsList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemDTO>findById(@PathVariable Long id) {
        ItemDTO dto = service.findById(id);

        return ResponseEntity.ok().body(dto);
    }
}
