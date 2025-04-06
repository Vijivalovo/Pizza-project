package com.example.products.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.products.config.MessageClass;
import com.example.products.models.ProductIngrs;
import com.example.products.service.ProductIngrService;

@RestController
@RequestMapping("/api/productIngrs")
public class ProductIngrController
{
    @Autowired
    private ProductIngrService productIngrService;

    @PostMapping("/createProductIngr")
    public ResponseEntity<ResponseClass<ProductIngrs>> createProductIngr(@RequestBody ProductIngrs productIngr)
    {
        ResponseClass<ProductIngrs> response = new ResponseClass<>("1", productIngrService.createProductIngr(productIngr));

        return ResponseEntity.status(201).body(response);
    }
    
    @PutMapping("/updateProductIngr")
    public ResponseEntity<ResponseClass<ProductIngrs>> updateProductIngr(@RequestBody ProductIngrs productIngr)
    {
        ResponseClass<ProductIngrs> response = new ResponseClass<>("1", productIngrService.updateProductIngr(productIngr));

        return ResponseEntity.status(200).body(response);
    } 
    
    @DeleteMapping("/deleteProductIngr/{id}")
    public ResponseEntity<ResponseClass<Void>> deleteProductIngr(@PathVariable int id)
    {
        productIngrService.deleteProductIngr(id);

        ResponseClass<Void> response = new ResponseClass<>("1", null);

        return ResponseEntity.status(200).body(response);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseClass<ProductIngrs>> findById(@PathVariable int id)
    {
        ResponseClass<ProductIngrs> response = new ResponseClass<>("1", productIngrService.findById(id));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseClass<List<ProductIngrs>>> getAll()
    {
        ResponseClass<List<ProductIngrs>> response = new ResponseClass<>("1", productIngrService.getAll());

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getByProductId/{id}")
    public ResponseEntity<ResponseClass<List<ProductIngrs>>> getByProductId(@PathVariable int id)
    {
        ResponseClass<List<ProductIngrs>> response = new ResponseClass<>("1", productIngrService.getByProductId(id));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getByIngredientId/{id}")
    public ResponseEntity<ResponseClass<List<ProductIngrs>>> getByIngredientId(@PathVariable int id)
    {
        ResponseClass<List<ProductIngrs>> response = new ResponseClass<>("1", productIngrService.getByIngredientId(id));

        return ResponseEntity.status(200).body(response);
    }  
}
