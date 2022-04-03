package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.http.Response;
import com.example.diplomaprojectgeneticcode.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.diplomaprojectgeneticcode.util.Constant.SUCCESS;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryRepo categoryRepo;

    @GetMapping("")
    public ResponseEntity<Response<?>> getAllCategories() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(categoryRepo.findAll())
                        .build()
        );
    }
}
