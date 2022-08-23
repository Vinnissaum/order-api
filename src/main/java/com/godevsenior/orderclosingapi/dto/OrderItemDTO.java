package com.godevsenior.orderclosingapi.dto;

import com.godevsenior.orderclosingapi.entities.OrderItem;

import java.io.Serializable;

public class OrderItemDTO implements Serializable {

    private Long id;
    private Long orderId;
    private Long itemId;
    private Double quantity;
    private Double totalValue;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderItem entity) {
        this.id = entity.getId();
        this.orderId = entity.getOrderId();
        this.itemId = entity.getItemId();
        this.quantity = entity.getQuantity();
        this.totalValue = entity.getTotalValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
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