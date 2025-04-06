package com.example.products.service;

import com.example.products.DTO.Product.CreateProductDTO;
import com.example.products.DTO.Product.UpdateProductDTO;
import com.example.products.config.MessageClass;
import com.example.products.errors.exceptions.categoryExceptions.CategoryNotFound;
import com.example.products.errors.exceptions.productIngrExceptions.ProductNotFound;
import com.example.products.models.Categories;
import com.example.products.models.Products;
import com.example.products.repository.ProductRepository;
import com.example.products.repository.CategoryRepository;
import com.example.products.service.Interfaces.ProductInterfaces;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @Async
    public Products createProduct(CreateProductDTO request)
    {
        Categories category = categoryRepository.findById(request.getCategory_id())        
        .orElseThrow(() -> new CategoryNotFound(MessageClass.CATEGORY_NOT_FOUND + request.getCategory_id()));

        Products product = modelMapper.map(request, Products.class);
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Async
    public Products updateProduct(UpdateProductDTO request)
    {
        Products OldProduct = productRepository.findById(request.getId())
        .orElseThrow(() -> new ProductNotFound(MessageClass.PRODUCT_NOT_FOUND + request.getId()));

        Products product = modelMapper.map(request, Products.class);
        product.setId(OldProduct.getId());
        product.setCategory(OldProduct.getCategory());

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
        throw new ProductNotFound(MessageClass.PRODUCT_NOT_FOUND + id);
    }

    @Async
    public Products findById(int id)
    {
        return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFound(MessageClass.PRODUCT_NOT_FOUND + id));

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