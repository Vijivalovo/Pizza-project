package com.example.products.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "productIngrs")
public class ProductIngrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredients ingredients;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products products;
}
