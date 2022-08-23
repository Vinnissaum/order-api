package com.godevsenior.orderclosingapi.dto;
import java.util.List;
import com.godevsenior.orderclosingapi.entities.Order;

import java.io.Serializable;
import java.time.Instant;

public class OrderDTO implements Serializable {

    private Long id;
    private Integer number;
    private Instant date;
    private Double percentageDiscount;
    private Double totalValue;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Integer number, Instant date, Double percentageDiscount, Double totalValue) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.percentageDiscount = percentageDiscount;
        this.totalValue = totalValue;
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.number = entity.getNumber();
        this.date = entity.getDate();
        this.percentageDiscount = entity.getPercentageDiscount();
        this.totalValue = entity.getTotalValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

}
