package com.example.diplomaprojectgeneticcode;

import com.example.diplomaprojectgeneticcode.dto.CategoryDto;
import com.example.diplomaprojectgeneticcode.entity.Category;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.enums.Status;
import com.example.diplomaprojectgeneticcode.repo.CategoryRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.math.BigDecimal;
import java.util.HashSet;

@SpringBootApplication
@EnableAsync
public class DiplomaProjectGeneticCodeApplication implements CommandLineRunner {
    @Autowired
    CourseService courseService;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DiplomaProjectGeneticCodeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        categoryService.createCategory(new Category("Development", null));


        User user = new User("name", "surname", "email", "pass", "bio", Status.ACTIVE, "image", true, false);

        User newUser = userService.create(user);
        System.out.println(newUser);

        Course course = new Course("titile",
                "subtitile",
                "desc",
                "image",
                null,
                "benefits",
                "reqs",
                CourseLevel.BEGINNER_LEVEL,
                "Englsi",
                "wel",
                "congrs",
                new BigDecimal(50000),
                Status.ACTIVE,
                categoryRepo.findCategoryByName("Development"));

        course.addTeacher(newUser);



        Course course1 = courseService.createCourse(course);

        System.out.println("sa");

    }
}


/*
* #TODO:
*   - Registration And Sign in services: in work
*   - Sending mail and confirming it: in work
*
*
*
* */
