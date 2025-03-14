package com.example.products.service.Interfaces;

import com.example.products.models.Categories;
import java.util.List;

public interface CategoryInterfaces
{
    Categories createCategory(Categories category);
    Categories updateCategory(Categories category);
    void deleteCategory(int id);
    Categories findById(int id);

    List<Categories> getAll();
}
