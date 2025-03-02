package com.example.products.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "size", nullable = true)
    private Integer size;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;
}
