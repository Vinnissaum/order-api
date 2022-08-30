package com.godevsenior.orderclosingapi.dto;

import com.godevsenior.orderclosingapi.entities.Order;
import com.godevsenior.orderclosingapi.entities.OrderItem;
import java.util.ArrayList;
import java.util.List;

public class OrderCloseDTO extends OrderDTO {
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderCloseDTO() {}

    public OrderCloseDTO(Order entity, List<OrderItem> orderItems) {
        super(entity);
        orderItems.forEach(item -> this.items.add(new OrderItemDTO(item)));
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
