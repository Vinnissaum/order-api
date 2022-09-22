package com.godevsenior.orderclosingapi.entities.enums;

public enum ItemType {
    P("Product"),
    S("Service");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
