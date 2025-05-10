package com.example.products.service.Interfaces;

import com.example.products.models.ProductIngrs;
import java.util.List;

public interface ProductIngrInterfaces
{
    ProductIngrs createProductIngr(ProductIngrs productIngr);
    ProductIngrs updateProductIngr(ProductIngrs productIngr);
    void deleteProductIngr(int id);
    ProductIngrs findById(int id);

    List<ProductIngrs> getAll();
    List<ProductIngrs> getByProductId(int id);
    List<ProductIngrs> getByIngredientId(int id);
}
