package com.example.products.service.Interfaces;

import com.example.products.models.Products;
import java.util.List;

public interface ProductInterfaces
{
    Products createProduct(Products product);
    Products updateProduct(Products product);
    void deleteProduct(int id);
    Products findById(int id);

    List<Products> getAll();
    List<Products> getByCategoryId(int id);
}
