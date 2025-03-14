package com.example.products.service;

import com.example.products.models.Products;
import com.example.products.repository.ProductRepository;
import com.example.products.service.Interfaces.ProductInterfaces;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductInterfaces
{
    @Autowired
    private ProductRepository productRepository;

    @Async
    public Products createProduct(Products product)
    {
        return productRepository.save(product);
    }

    @Async
    public Products updateProduct(Products product)
    {
        if(productRepository.existsById(product.getId()))
        {
            return productRepository.save(product);
        }
        throw new RuntimeException("Product not found");
    }

    @Async
    public void deleteProduct(int id)
    {
        Optional<Products> product = productRepository.findById(id);

        if(product.isPresent())
        {
            productRepository.deleteById(id);
        }
        throw new RuntimeException("Product not found");
    }

    @Async
    public Products findById(int id)
    {
        return productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    }

    @Async
    public List<Products> getAll()
    {
        return productRepository.findAll();
    }

    @Async
    public List<Products> getByCategoryId(int id)
    {
        List<Products> products = productRepository.findAll();

        List<Products> filteredProductIngrs = new ArrayList<>();

        for (Products product : products)
        {
            if (product.getCategory().equals(id))
            {
                filteredProductIngrs.add(product);
            }
        }

        return filteredProductIngrs;
    }
}