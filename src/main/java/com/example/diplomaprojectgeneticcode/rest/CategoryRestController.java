package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.CategoryDTO;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.mapper.CategoryMapper;
import com.example.diplomaprojectgeneticcode.repo.CategoryRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.diplomaprojectgeneticcode.util.Constant.SUCCESS;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryRestController extends AbstractRestController{

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<CategoryDTO>>> getAllCategories() {
        return successOK(categoryMapper.toDto(categoryService.getAllCategories()));
    }


    @GetMapping("/popular")
    public ResponseEntity<ResponseDTO<List<CategoryDTO>>> getPopularCategories() {
        return successOK(categoryMapper.toDto(categoryService.getPopularCategories()));
    }


}
