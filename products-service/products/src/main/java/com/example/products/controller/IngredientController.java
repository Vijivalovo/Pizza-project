package com.example.products.controller;

import com.example.products.models.Ingredients;
import com.example.products.service.IngredientService;

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

public class IngredientController
{
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("api/ingredients/createIngredient/")
    public ResponseEntity<Map<String, Object>> createIngredient(@RequestBody Ingredients ingredient)
    {
        try
        {
            Ingredients ingredientNew = ingredientService.createIngredient(ingredient);
            Map<String, Object> response = new HashMap<>();
            response.put("body", ingredientNew);
            response.put("message", "Ингредиент создан");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при создании ингредиента");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @PutMapping("api/ingredients/updateIngredient/")
    public ResponseEntity<Map<String, Object>> updateIngredient(@RequestBody Ingredients ingredient)
    {
        try
        {
            Ingredients ingredientNew = ingredientService.updateIngredient(ingredient);
            Map<String, Object> response = new HashMap<>();
            response.put("body", ingredientNew);
            response.put("message", "Обновление ингредиента");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при обновлении ингредиетна");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    } 
    
    @DeleteMapping("api/ingredients/deleteIngredient/{id}")
    public ResponseEntity<Map<String, Object>> deleteIngredient(@PathVariable int id)
    {
        try
        {
            ingredientService.deleteIngredient(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Ингредиент удалён");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при удалении ингредиента");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("api/ingredients/findById/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id)
    {
        try
        {
            Ingredients ingredient = ingredientService.findById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", ingredient);
            response.put("message", "Ингредиент найден");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске ингредиента");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/ingredients/getAll/")
    public ResponseEntity<Map<String, Object>> getAll()
    {
        try
        {
            List<Ingredients> ingredients = ingredientService.getAll();
            Map<String, Object> response = new HashMap<>();
            response.put("body", ingredients);
            response.put("message", "Все ингредиенты найдены");
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
}
