package com.example.products.errors.exceptions.productIngrExceptions;

public class ProductNotFound extends RuntimeException
{
    public ProductNotFound(String message)
    {
        super(message);
    }
}
