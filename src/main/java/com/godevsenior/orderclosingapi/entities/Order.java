package com.godevsenior.orderclosingapi.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;
    private Integer number;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;
    private Double percentageDiscount;
    private Double totalValue;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    public Order() {}

    public Order(UUID id, Integer number, Instant date, Double percentageDiscount) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.percentageDiscount = percentageDiscount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
        if (orderItems != null) {
            double productWithDiscount = orderItems.stream().filter(orderItem -> Objects.equals(orderItem.getItem().getType().toString(), "P"))
                    .map(x -> x.getItem().getCost()).reduce(0.0, Double::sum) * percentageDiscount;
            totalValue = productWithDiscount + orderItems.stream().filter(orderItem -> Objects.equals(orderItem.getItem().getType().toString(), "S"))
                    .map(x -> x.getItem().getCost()).reduce(0.0, Double::sum);
        }
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
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
