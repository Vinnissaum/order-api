package com.godevsenior.orderclosingapi.dto;

import com.godevsenior.orderclosingapi.entities.OrderItem;

import java.io.Serializable;
import java.util.UUID;

public class OrderItemDTO implements Serializable {

    private UUID id;
    private UUID itemId;
    private Double quantity;
    private Double totalValue;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderItem entity) {
        this.id = entity.getId();
        this.itemId = entity.getItem().getId();
        this.quantity = entity.getQuantity();
        this.totalValue = entity.getTotalValue();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}