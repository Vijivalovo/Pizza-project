package com.example.products.errors.exceptions.ingredientExceptions;

public class IngredientNotFound extends RuntimeException
{
    public IngredientNotFound(String message)
    {
        super(message);
    }
}
