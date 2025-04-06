package com.example.products.controller;

import com.example.products.config.MessageClass;
import com.example.products.models.Ingredients;
import com.example.products.service.IngredientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController
{
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/createIngredient")
    public ResponseEntity<ResponseClass<Ingredients>> createIngredient(@RequestBody Ingredients ingredient)
    {
        ResponseClass<Ingredients> response = new ResponseClass<>(MessageClass.INGREDIENT_CREATED, ingredientService.createIngredient(ingredient));

        return ResponseEntity.status(201).body(response);
    }
    
    @PutMapping("/updateIngredient")
    public ResponseEntity<ResponseClass<Ingredients>> updateIngredient(@RequestBody Ingredients ingredient)
    {
        ResponseClass<Ingredients> response = new ResponseClass<>(MessageClass.INGREDIENT_UPDATED, ingredientService.updateIngredient(ingredient));

        return ResponseEntity.status(200).body(response);
    } 
    
    @DeleteMapping("/deleteIngredient/{id}")
    public ResponseEntity<ResponseClass<Void>> deleteIngredient(@PathVariable int id)
    {
        ingredientService.deleteIngredient(id);

        ResponseClass<Void> response = new ResponseClass<>(MessageClass.INGREDIENT_DELETED, null);

        return ResponseEntity.status(200).body(response);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseClass<Ingredients>> findById(@PathVariable int id)
    {
        ResponseClass<Ingredients> response = new ResponseClass<>(MessageClass.INGREDIENT_FINDBYID + id, ingredientService.findById(id));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseClass<List<Ingredients>>> getAll()
    {
        ResponseClass<List<Ingredients>> response = new ResponseClass<>(MessageClass.INGREDIENT_GETALL, ingredientService.getAll());

        return ResponseEntity.status(200).body(response);
    }    
}
