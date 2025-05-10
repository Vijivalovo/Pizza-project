package com.example.products.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.products.models.ProductIngrs;
import com.example.products.service.ProductIngrService;

@RestController
public class ProductIngrController
{
    @Autowired
    private ProductIngrService productIngrService;

    @PostMapping("api/productIngrs/createProductIngr/")
    public ResponseEntity<Map<String, Object>> createProductIngr(@RequestBody ProductIngrs productIngr)
    {
        try
        {
            ProductIngrs productIngrNew = productIngrService.createProductIngr(productIngr);
            Map<String, Object> response = new HashMap<>();
            response.put("body", productIngrNew);
            response.put("message", "Создание");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при создании");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @PutMapping("api/productIngrs/updateProductIngr/")
    public ResponseEntity<Map<String, Object>> updateProductIngr(@RequestBody ProductIngrs productIngr)
    {
        try
        {
            ProductIngrs productIngrNew = productIngrService.updateProductIngr(productIngr);
            Map<String, Object> response = new HashMap<>();
            response.put("body", productIngrNew);
            response.put("message", "Обновление");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при обновлении");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    } 
    
    @DeleteMapping("api/productIngrs/deleteProductIngr/{id}")
    public ResponseEntity<Map<String, Object>> deleteProductIngr(@PathVariable int id)
    {
        try
        {
            productIngrService.deleteProductIngr(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Ингредиент продукта удалён");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при удалении ингредиента продукта");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("api/productIngrs/findById/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id)
    {
        try
        {
            ProductIngrs productIngr = productIngrService.findById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", productIngr);
            response.put("message", "Ингредиент продукта найден");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске ингредиента продукта");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/productIngrs/getAll/")
    public ResponseEntity<Map<String, Object>> getAll()
    {
        try
        {
            List<ProductIngrs> productIngrs = productIngrService.getAll();
            Map<String, Object> response = new HashMap<>();
            response.put("body", productIngrs);
            response.put("message", "Все ингредиенты всех продуктов найдены");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске всех ингредиентов");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/productIngrs/getByProductId/{id}")
    public ResponseEntity<Map<String, Object>> getByProductId(@PathVariable int id)
    {
        try
        {
            List<ProductIngrs> productIngrs = productIngrService.getByProductId(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", productIngrs);
            response.put("message", "Ингредиенты продукта найдены");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске ингредиентов продукта");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/productIngrs/getByIngredientId/{id}")
    public ResponseEntity<Map<String, Object>> getByIngredientId(@PathVariable int id)
    {
        try
        {
            List<ProductIngrs> productIngrs = productIngrService.getByIngredientId(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", productIngrs);
            response.put("message", "Ингредиенты продукта найдены");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске ингредиентов продукта");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }  
}
