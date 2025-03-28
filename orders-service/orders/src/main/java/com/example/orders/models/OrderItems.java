package com.example.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "\"orderItems\"")
public class OrderItems {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer product_id;

    @ManyToOne
    @JoinColumn(name = "\"order_id\"", nullable = false)
    @JsonIgnore
    private Orders order;

    public Integer getOrderId() {
        return order != null ? order.getId() : null;
    }
}
