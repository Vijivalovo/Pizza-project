package com.example.products.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "\"productIngrs\"")
public class ProductIngrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    @JsonIgnore
    private Ingredients ingredients;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Products products;
}
