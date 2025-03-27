package com.example.products.DTO.Product;

import lombok.Data;

@Data
public class UpdateProductDTO
{
    Integer id;
    String name;
    Integer size;
    Integer weight;    
}
