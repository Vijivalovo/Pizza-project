package com.example.products.repository;

import com.example.products.models.ProductIngrs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIngrRepository extends JpaRepository<ProductIngrs, Integer>
{

    List<ProductIngrs> getByProductId(int id);

    List<ProductIngrs> getByIngredientId(int id);
    
}
