package com.example.products.service.Interfaces;

import com.example.products.models.Ingredients;
import java.util.List;

public interface IngredientInterfaces
{
    Ingredients createIngredient(Ingredients ingredient);
    Ingredients updateIngredient(Ingredients ingredient);
    void deleteIngredient(int id);
    Ingredients findById(int id);
    
    List<Ingredients> getAll();
}
