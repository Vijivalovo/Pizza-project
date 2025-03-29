package com.example.orders.models;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "\"orders\"")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Basic
    @Column(name = "type_paymnet", nullable = false)
    private String type_paymnet;

    @Column(name = "type_delivery", nullable = true)
    private String type_delivery;

     @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "coupon", nullable = false)
    private String coupon;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems = new ArrayList<>();
}
