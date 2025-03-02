package com.example.orders.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orderItems")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer product_id;
}
