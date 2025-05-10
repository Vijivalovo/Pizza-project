package com.example.products.service;

import com.example.products.models.Categories;
import com.example.products.repository.CategoryRepository;
import com.example.products.service.Interfaces.CategoryInterfaces;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryInterfaces
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Async
    public Categories createCategory(Categories category)
    {
        return categoryRepository.save(category);
    }

    @Async
    public Categories updateCategory(Categories category)
    {
        if(categoryRepository.existsById(category.getId()))
        {
            return categoryRepository.save(category);
        }
        throw new RuntimeException("Categorie not found");
    }

    @Async
    public void deleteCategory(int id)
    {
        Optional<Categories> category = categoryRepository.findById(id);

        if(category.isPresent())
        {
            categoryRepository.deleteById(id);
        }
        throw new RuntimeException("Categorie not found");
    }

    @Async
    public Categories findById(int id)
    {
        return categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Categorie not found"));

    }

    @Async
    public List<Categories> getAll()
    {
        return categoryRepository.findAll();
    }
}