package com.example.orders.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
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

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @ManyToOne
    @JoinColumn(name = "orderItem_id", nullable = false)
    private OrderItems orderItems;
}
