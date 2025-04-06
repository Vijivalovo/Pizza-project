package com.example.products.errors.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.products.errors.BusinessError;
import com.example.products.errors.exceptions.categoryExceptions.CategoryNotFound;
import com.example.products.errors.exceptions.ingredientExceptions.IngredientNotFound;
import com.example.products.errors.exceptions.productExceptions.ProductIngrNotFound;
import com.example.products.errors.exceptions.productIngrExceptions.ProductNotFound;

public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BusinessError> Exception(Exception e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(500).body(err);
    }

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<BusinessError> CategoryNotFound(CategoryNotFound e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(IngredientNotFound.class)
    public ResponseEntity<BusinessError> IngredientNotFound(IngredientNotFound e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(ProductIngrNotFound.class)
    public ResponseEntity<BusinessError> ProductIngrNotFound(ProductIngrNotFound e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<BusinessError> ProductNotFound(ProductNotFound e)
    {
        BusinessError err = new BusinessError(e.getMessage());

        return ResponseEntity.status(404).body(err);
    }
}
