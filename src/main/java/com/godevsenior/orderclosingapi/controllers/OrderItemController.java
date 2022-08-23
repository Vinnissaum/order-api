package com.godevsenior.orderclosingapi.controllers;

import com.godevsenior.orderclosingapi.dto.OrderItemDTO;
import com.godevsenior.orderclosingapi.services.OrderItemService;
import com.godevsenior.orderclosingapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderItemController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService service;

    @GetMapping(value = "/{order}/items")
    public ResponseEntity<List<OrderItemDTO>> findOrderItems(@PathVariable Long order) {
        List<OrderItemDTO> ordersList = service.findAll(order);

        return ResponseEntity.ok().body(ordersList);
    }

    @GetMapping(value = "/{order}/items/{id}")
    public ResponseEntity<OrderItemDTO> findById(@PathVariable Long id) {
        OrderItemDTO dto = service.findById(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/{id}/items")
    public ResponseEntity<OrderItemDTO> insert(@PathVariable Long id, @RequestBody OrderItemDTO dto) {
        dto = service.insert(id, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{order}/items/{id}")
    public ResponseEntity<OrderItemDTO> update(@PathVariable Long id, @RequestBody OrderItemDTO dto) {
        dto = service.update(id, dto);

        return ResponseEntity.ok().body(dto);
    }
//
    @DeleteMapping(value = "/{order}/items/{id}")
    public ResponseEntity<OrderItemDTO> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
