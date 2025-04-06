package com.example.products.service;

import com.example.products.config.MessageClass;
import com.example.products.errors.exceptions.ingredientExceptions.IngredientNotFound;
import com.example.products.models.Ingredients;
import com.example.products.service.Interfaces.IngredientInterfaces;
import com.example.products.repository.IngredientRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService implements IngredientInterfaces
{
    @Autowired
    private IngredientRepository ingredientRepository;

    @Async
    public Ingredients createIngredient(Ingredients ingredient)
    {
        return ingredientRepository.save(ingredient);
    }

    @Async
    public Ingredients updateIngredient(Ingredients ingredient)
    {
        if(ingredientRepository.existsById(ingredient.getId()))
        {
            return ingredientRepository.save(ingredient);
        }
        throw new IngredientNotFound(MessageClass.INGREDIENT_NOT_FOUND + ingredient.getId());
    }

    @Async
    public void deleteIngredient(int id)
    {
        Optional<Ingredients> ingredient = ingredientRepository.findById(id);

        if(ingredient.isPresent())
        {
            ingredientRepository.deleteById(id);
        }
        throw new IngredientNotFound(MessageClass.INGREDIENT_NOT_FOUND + id);
    }

    @Async
    public Ingredients findById(int id)
    {
        return ingredientRepository.findById(id)
        .orElseThrow(() -> new IngredientNotFound(MessageClass.INGREDIENT_NOT_FOUND + id));

    }

    @Async
    public List<Ingredients> getAll()
    {
        return ingredientRepository.findAll();
    }
}
