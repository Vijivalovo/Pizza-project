package com.example.products.repository;

import com.example.products.models.Products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer>
{

    List<Products> getByCategoryId(int id);
    
}
