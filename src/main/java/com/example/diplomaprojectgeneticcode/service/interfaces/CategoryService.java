package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getPopularCategories();
    Category getCategoryById(Integer id);
    List<Category> getAllCategories();
    Category getCategoryByName(String name);
}
