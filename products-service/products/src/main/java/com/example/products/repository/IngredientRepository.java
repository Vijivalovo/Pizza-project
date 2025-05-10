package com.example.products.repository;

import com.example.products.models.Ingredients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredients, Integer>
{
    
}
