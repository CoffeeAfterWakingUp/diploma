package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.dto.CategoryDto;
import com.example.diplomaprojectgeneticcode.entity.Category;
import com.example.diplomaprojectgeneticcode.repo.CategoryRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }
}
