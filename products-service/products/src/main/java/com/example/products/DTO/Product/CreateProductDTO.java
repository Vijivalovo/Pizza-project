package com.example.products.DTO.Product;

import lombok.Data;

@Data
public class CreateProductDTO
{
    String name;
    Integer size;
    Integer weight;
    Integer category_id;    
}
