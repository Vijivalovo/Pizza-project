package com.example.products.controller;

import com.example.products.config.MessageClass;
import com.example.products.models.Categories;
import com.example.products.service.CategoryService;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<ResponseClass<Categories>> createCategory(@RequestBody Categories category)
    {
        ResponseClass<Categories> response = new ResponseClass<>("1", categoryService.createCategory(category));

        return ResponseEntity.status(201).body(response);
    }
    
    @PutMapping("/updateCategory")
    public ResponseEntity<ResponseClass<Categories>> updateCategory(@RequestBody Categories category)
    {
        ResponseClass<Categories> response = new ResponseClass<>("1", categoryService.updateCategory(category));

        return ResponseEntity.status(200).body(response);
    } 
    
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<ResponseClass<Void>> deleteCategory(@PathVariable int id)
    {
        categoryService.deleteCategory(id);

        ResponseClass<Void> response = new ResponseClass<>("1", null);

        return ResponseEntity.status(200).body(response);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseClass<Categories>> findById(@PathVariable int id)
    {
        ResponseClass<Categories> response = new ResponseClass<>("1", categoryService.findById(id));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseClass<List<Categories>>> getAll()
    {
        ResponseClass<List<Categories>> response = new ResponseClass<>("1", categoryService.getAll());

        return ResponseEntity.status(200).body(response);
    }    
}
