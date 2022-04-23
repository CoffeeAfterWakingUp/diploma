package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.CategoryDTO;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.entity.Category;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CategoryMapper {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryDTO toDto(Category category) {
        log.info("Category: {}", category);
        Optional<Category> categoryOpt = Optional.ofNullable(category);
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryOpt.map(Category::getId).ifPresent(categoryDTO::setId);
        categoryOpt.map(Category::getName).ifPresent(categoryDTO::setName);
        categoryOpt.map(Category::getParentId).ifPresent(parentId -> {
            Category parentCategory = categoryService.getCategoryById(parentId);
            Optional.ofNullable(parentCategory).ifPresent(c -> categoryDTO.setParentCategory(c.getName()));
        });

        log.info("CategoryDTO: {}", categoryDTO);

        return categoryDTO;
    }

    public List<CategoryDTO> toDto(List<Category> categories) {
        return Optional.ofNullable(categories)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
