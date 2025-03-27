package com.example.products.service.Interfaces;

import com.example.products.DTO.Product.CreateProductDTO;
import com.example.products.DTO.Product.UpdateProductDTO;
import com.example.products.models.Products;
import java.util.List;

public interface ProductInterfaces
{
    Products createProduct(CreateProductDTO request);
    Products updateProduct(UpdateProductDTO request);
    void deleteProduct(int id);
    Products findById(int id);

    List<Products> getAll();
    List<Products> getByCategoryId(int id);
}
