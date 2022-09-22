package com.godevsenior.orderclosingapi.dto;

import com.godevsenior.orderclosingapi.entities.Item;
import com.godevsenior.orderclosingapi.entities.enums.ItemType;

import java.io.Serializable;
import java.util.UUID;

public class ItemDTO implements Serializable {
    private UUID id;
    private String description;
    private Double cost;
    private ItemType type;

    public ItemDTO() {}

    public ItemDTO(UUID id, String description, Double cost, ItemType type) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.type = type;
    }

    public ItemDTO(Item entity) {
        this.id = entity.getId();
        this.cost = entity.getCost();
        this.description = entity.getDescription();
        this.type = entity.getType();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
