package com.example.diplomaprojectgeneticcode;

import com.example.diplomaprojectgeneticcode.entity.*;
import com.example.diplomaprojectgeneticcode.enums.ContentType;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.enums.Status;
import com.example.diplomaprojectgeneticcode.repo.*;
import com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableAsync
public class DiplomaProjectGeneticCodeApplication implements CommandLineRunner {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CourseStudentRepo courseStudentRepo;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    SectionRepo sectionRepo;

    @Autowired
    ContentRepo contentRepo;

    public static void main(String[] args) {
        SpringApplication.run(DiplomaProjectGeneticCodeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        categoryService.createCategory(new Category("Development", null));
        categoryService.createCategory(new Category("Testing", null));


        User user = new User("name", "surname", "180103273@stu.sdu.edu.kz", passwordEncoder.encode("pass"), "bio", Status.ACTIVE, "image", true, false);

        User newUser = userService.create(user);
        System.out.println(newUser);

        Course course = new Course(
                "React JS",
                "subtitile",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "e0df29d3-2ba1-492f-919f-01c15c99742c.f703a951-3527-4a7b-be41-948492470ea1.jpg",
                null,
                "benefits",
                "reqs",
                CourseLevel.BEGINNER_LEVEL,
                "Englsi",
                "wel",
                "congrs",
                new BigDecimal(40000),
                Status.ACTIVE,
                categoryRepo.findCategoryByName("Development"));

        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());

        course.addTeacher(newUser);



        Course course1 = new Course(
                "Java SE",
                "subtitile",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "e0df29d3-2ba1-492f-919f-01c15c99742c.f703a951-3527-4a7b-be41-948492470ea1.jpg",
                null,
                "benefits",
                "reqs",
                CourseLevel.BEGINNER_LEVEL,
                "Englsi",
                "wel",
                "congrs",
                new BigDecimal(50000),
                Status.ACTIVE,
                categoryRepo.findCategoryByName("Testing"));

        course1.setCreatedAt(LocalDateTime.now());
        course1.setUpdatedAt(LocalDateTime.now());

        course1.addTeacher(newUser);




        Course course2 = new Course("Java Spring",
                "subtitile",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "e0df29d3-2ba1-492f-919f-01c15c99742c.f703a951-3527-4a7b-be41-948492470ea1.jpg",
                null,
                "benefits",
                "reqs",
                CourseLevel.BEGINNER_LEVEL,
                "Englsi",
                "wel",
                "congrs",
                new BigDecimal(30000),
                Status.ACTIVE,
                categoryRepo.findCategoryByName("Development"));

        course2.setCreatedAt(LocalDateTime.now().minusDays(10));
        course2.setUpdatedAt(LocalDateTime.now().minusDays(10));


        Course courseCreated = courseService.createCourse(course);
        Course course1Created = courseService.createCourse(course1);
        Course course2Created = courseService.createCourse(course2);


        Review review = new Review(null, "good", 5, newUser, course, LocalDateTime.now());
        Review review1 = new Review(null, "good", 4, newUser, course1, LocalDateTime.now());
        Review review2 = new Review(null, "good", 3, newUser, course2, LocalDateTime.now());
        Review review3 = new Review(null, "good", 2, newUser, course1, LocalDateTime.now());
        Review review4 = new Review(null, "good", 1, newUser, course1, LocalDateTime.now());
        Review review5 = new Review(null, "good", 5, newUser, course, LocalDateTime.now());

        reviewRepo.save(review);
        reviewRepo.save(review1);
        reviewRepo.save(review2);
        reviewRepo.save(review3);
        reviewRepo.save(review4);
        reviewRepo.save(review5);


        courseStudentRepo.save(new CourseStudent(course1Created, newUser));
//        courseStudentRepo.save(new CourseStudent(courseCreated, newUser));
        courseStudentRepo.save(new CourseStudent(course2Created, newUser));

        Section section = sectionRepo.save(
                new Section("Introduction", 1L)
        );

        Section section1 = sectionRepo.save(
                new Section("Maching Learning 101", 2L)
        );

        contentRepo.save(
                new Content("Course Outline", ContentType.VIDEO, section, courseCreated, "69e2df96-a08f-4e27-9c50-5ad03d98fc1c.week6.mp4")
        );

        contentRepo.save(
                new Content("Are you getting it?", ContentType.ARTICLE, section, courseCreated, "50e13a85-c899-4aaf-8039-390aaf9a967b.сырбек.pdf")
        );

        contentRepo.save(
                new Content("Course Outline 1", ContentType.VIDEO, section1, courseCreated, "")
        );

        contentRepo.save(
                new Content("Are you getting it? 1", ContentType.LECTURE, section1, courseCreated,  "")
        );






    }
}

