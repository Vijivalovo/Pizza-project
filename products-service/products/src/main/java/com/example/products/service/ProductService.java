package com.example.products.service;

import com.example.products.DTO.Product.CreateProductDTO;
import com.example.products.DTO.Product.UpdateProductDTO;
import com.example.products.errors.exceptions.categoryExceptions.CategoryNotFound;
import com.example.products.errors.exceptions.productIngrExceptions.ProductNotFound;
import com.example.products.models.Categories;
import com.example.products.models.Products;
import com.example.products.repository.ProductRepository;
import com.example.products.repository.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Async
    public Products createProduct(CreateProductDTO request)
    {
        Categories category = categoryRepository.findById(request.getCategory_id())        
        .orElseThrow(() -> new CategoryNotFound("Category not found"));

        Products product = new Products();
        product.setName(request.getName());
        product.setSize(request.getSize());
        product.setWeight(request.getWeight());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Async
    public Products updateProduct(UpdateProductDTO request)
    {
        Products OldProduct = productRepository.findById(request.getId())
        .orElseThrow(() -> new ProductNotFound("Product not found"));

        Products product = new Products();
        product.setId(OldProduct.getId());
        product.setCategory(OldProduct.getCategory());
        product.setName(request.getName());
        product.setSize(request.getSize());
        product.setWeight(request.getWeight());

        return productRepository.save(product);
    }

    @Async
    public void deleteProduct(int id)
    {
        Optional<Products> product = productRepository.findById(id);

        if(product.isPresent())
        {
            productRepository.deleteById(id);
        }
        throw new ProductNotFound("Product not found");
    }

    @Async
    public Products findById(int id)
    {
        return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFound("Product not found"));

    }

    @Async
    public List<Products> getAll()
    {
        return productRepository.findAll();
    }

    @Async
    public List<Products> getByCategoryId(int id)
    {
        return productRepository.getByCategoryId(id);
    }
}