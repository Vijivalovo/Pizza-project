package com.example.products.controller;

import com.example.products.models.Categories;
import com.example.products.service.CategoryService;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @PostMapping("api/categories/createCategory/")
    public ResponseEntity<Map<String, Object>> createCategory(@RequestBody Categories category)
    {
        try
        {
            Categories categoryNew = categoryService.createCategory(category);
            Map<String, Object> response = new HashMap<>();
            response.put("body", categoryNew);
            response.put("message", "Категория создана");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при создании категории");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @PutMapping("api/categories/updateCategory/")
    public ResponseEntity<Map<String, Object>> updateCategory(@RequestBody Categories category)
    {
        try
        {
            Categories categoryNew = categoryService.updateCategory(category);
            Map<String, Object> response = new HashMap<>();
            response.put("body", categoryNew);
            response.put("message", "Категория обновлена");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при обновлении категории");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    } 
    
    @DeleteMapping("api/categories/deleteCategory/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable int id)
    {
        try
        {
            categoryService.deleteCategory(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Категория удалена");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при удалении категории");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("api/categories/findById/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id)
    {
        try
        {
            Categories category = categoryService.findById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", category);
            response.put("message", "Категория найдена");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске категории");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/categories/getAll/")
    public ResponseEntity<Map<String, Object>> getAll()
    {
        try
        {
            List<Categories> categories = categoryService.getAll();
            Map<String, Object> response = new HashMap<>();
            response.put("body", categories);
            response.put("message", "Категории найдены");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске категорий");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }    
}
