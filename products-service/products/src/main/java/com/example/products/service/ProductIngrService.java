package com.example.products.service;

import com.example.products.errors.exceptions.productExceptions.ProductIngrNotFound;
import com.example.products.models.ProductIngrs;
import com.example.products.repository.ProductIngrRepository;
import com.example.products.service.Interfaces.ProductIngrInterfaces;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductIngrService implements ProductIngrInterfaces
{
    @Autowired
    private ProductIngrRepository productIngrRepository;

    @Async
    public ProductIngrs createProductIngr(ProductIngrs productIngr)
    {
        return productIngrRepository.save(productIngr);
    }

    @Async
    public ProductIngrs updateProductIngr(ProductIngrs productIngr)
    {
        if(productIngrRepository.existsById(productIngr.getId()))
        {
            return productIngrRepository.save(productIngr);
        }
        throw new ProductIngrNotFound("ProductIngr not found");
    }

    @Async
    public void deleteProductIngr(int id)
    {
        Optional<ProductIngrs> productIngr = productIngrRepository.findById(id);

        if(productIngr.isPresent())
        {
            productIngrRepository.deleteById(id);
        }
        throw new ProductIngrNotFound("ProductIngr not found");
    }

    @Async
    public ProductIngrs findById(int id)
    {
        return productIngrRepository.findById(id)
        .orElseThrow(() -> new ProductIngrNotFound("ProductIngr not found"));

    }

    @Async
    public List<ProductIngrs> getAll()
    {
        return productIngrRepository.findAll();
    }

    @Async
    public List<ProductIngrs> getByProductId(int id)
    {
        return productIngrRepository.getByProductId(id);
    }

    @Async
    public List<ProductIngrs> getByIngredientId(int id)
    {
        return productIngrRepository.getByIngredientId(id);
    }
}
