package com.example.products.controller;

import com.example.products.DTO.Product.CreateProductDTO;
import com.example.products.DTO.Product.UpdateProductDTO;
import com.example.products.models.Products;
import com.example.products.service.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController
{
     @Autowired
    private ProductService productService;

    @PostMapping("api/products/createProduct/")
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody CreateProductDTO request)
    {
        try
        {
            Products productNew = productService.createProduct(request);
            Map<String, Object> response = new HashMap<>();
            response.put("body", productNew);
            response.put("message", "Блюдо добавлено");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при добавлении блюда");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("api/products/updateProduct/")
    public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody UpdateProductDTO request)
    {
        try
        {
            Products productNew = productService.updateProduct(request);
            Map<String, Object> response = new HashMap<>();
            response.put("body", productNew);
            response.put("message", "Блюдо обновлено");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при обновлении блюда");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("api/products/deleteProduct/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable int id)
    {
        try
        {
            productService.deleteProduct(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Блюдо удалено");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при удалении блюда");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/products/findById/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id)
    {
        try
        {
            Products product = productService.findById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", product);
            response.put("message", "Блюдо найдено");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске блюда");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/products/getAll/")
    public ResponseEntity<Map<String, Object>> getAll()
    {
        try
        {
            List<Products> products = productService.getAll();
            Map<String, Object> response = new HashMap<>();
            response.put("body", products);
            response.put("message", "Все блюда найдены");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске блюд");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("api/products/getByCategoryId/{id}")
    public ResponseEntity<Map<String, Object>> getByCategoryId(@PathVariable int id)
    {
        try
        {
            List<Products> products = productService.getByCategoryId(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", products);
            response.put("message", "Блюдо по категории найдено");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске блюда по категории");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }   
}
