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
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    public Order() {}

    public Order(Long id, Integer number, Instant date, Double percentageDiscount) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.percentageDiscount = percentageDiscount;
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
        if (orderItems != null) {
            double productWithDiscount = orderItems.stream().filter(orderItem -> orderItem.getItem().getType() == 'P')
                    .map(x -> x.getItem().getCost()).reduce(0.0, Double::sum) * percentageDiscount;
            totalValue = productWithDiscount + orderItems.stream().filter(orderItem -> orderItem.getItem().getType() == 'S')
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
