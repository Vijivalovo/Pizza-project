package com.example.products.errors.exceptions.categoryExceptions;

public class CategoryNotFound extends RuntimeException
{
    public CategoryNotFound(String message)
    {
        super(message);
    }
}
