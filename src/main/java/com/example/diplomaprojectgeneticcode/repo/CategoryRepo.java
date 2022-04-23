package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Category findCategoryByName(String name);



    @Query("SELECT cat FROM Category cat " +
            "GROUP BY cat.id " +
            "ORDER BY cat.courses.size DESC")
    List<Category> getPopularCategories();
}
