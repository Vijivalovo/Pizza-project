package com.example.products.controller;

import com.example.products.DTO.Product.CreateProductDTO;
import com.example.products.DTO.Product.UpdateProductDTO;
import com.example.products.config.MessageClass;
import com.example.products.models.Products;
import com.example.products.service.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
     @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ResponseClass<Products>> createProduct(@RequestBody CreateProductDTO request)
    {
        ResponseClass<Products> response = new ResponseClass<>("1", productService.createProduct(request));

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ResponseClass<Products>> updateProduct(@RequestBody UpdateProductDTO request)
    {
        ResponseClass<Products> response = new ResponseClass<>("1", productService.updateProduct(request));

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<ResponseClass<Void>> deleteProduct(@PathVariable int id)
    {
        productService.deleteProduct(id);

        ResponseClass<Void> response = new ResponseClass<>("1", null);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseClass<Products>> findById(@PathVariable int id)
    {
        ResponseClass<Products> response = new ResponseClass<>("1", productService.findById(id));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseClass<List<Products>>> getAll()
    {
        ResponseClass<List<Products>> response = new ResponseClass<>("1", productService.getAll());

        return ResponseEntity.status(200).body(response);
    }
    
    @GetMapping("/getByCategoryId/{id}")
    public ResponseEntity<ResponseClass<List<Products>>> getByCategoryId(@PathVariable int id)
    {
        ResponseClass<List<Products>> response = new ResponseClass<>("1", productService.getByCategoryId(id));

        return ResponseEntity.status(200).body(response);
    }   
}
