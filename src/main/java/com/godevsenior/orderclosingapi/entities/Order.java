package com.godevsenior.orderclosingapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;
    private Double percentageDiscount;
    private Double totalValue;
    @OneToMany
    private List<OrderItem> orderItems;
    public Order() {}

    public Order(Long id, Integer number, Instant date, Double percentageDiscount, Double totalValue) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.percentageDiscount = percentageDiscount;
        this.totalValue = totalValue;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    @PrePersist
    public void prePersist() {
        date = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        date = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
