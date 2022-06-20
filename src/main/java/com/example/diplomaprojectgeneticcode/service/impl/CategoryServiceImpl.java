package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.entity.Category;
import com.example.diplomaprojectgeneticcode.repo.CategoryRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getPopularCategories() {
        return categoryRepo.getPopularCategories();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll(Sort.by("name"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepo.findCategoryByName(name);
    }
}
