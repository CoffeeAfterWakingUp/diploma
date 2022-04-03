package com.example.diplomaprojectgeneticcode.util;

import com.example.diplomaprojectgeneticcode.entity.Category;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.enums.Status;
import com.example.diplomaprojectgeneticcode.repo.CategoryRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Run {

    @Autowired
    CourseService courseService;

    @Autowired
    CategoryRepo categoryRepo;

    public static void main(String[] args) {

    }



}
