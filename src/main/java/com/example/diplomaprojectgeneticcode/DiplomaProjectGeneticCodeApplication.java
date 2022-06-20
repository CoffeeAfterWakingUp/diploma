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


        User user = new User("teacher", "teacher", "180103273@stu.sdu.edu.kz", passwordEncoder.encode("pass"), "bio", Status.ACTIVE, "44b9539c-8481-4139-88d7-34656b3f27e8.google.jpg", true, false);

        User newUser = userService.create(user);
        System.out.println(newUser);

        Course course = new Course(
                "React JS",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "f5c53ab1-d2bf-4e53-b44c-4850e8a3752f.react.png",
                null,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                CourseLevel.BEGINNER_LEVEL,
                "English",
                "wel",
                "congrs",
                new BigDecimal(40000),
                Status.ACTIVE,
                categoryRepo.findCategoryByName("Development"));

        course.setCurrency("KZT");

        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());

        course.addTeacher(newUser);


        Course course1 = new Course(
                "Java SE",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "87707f93-6b81-4b34-8f76-2f25b38b75be.javaSe.png",
                null,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                CourseLevel.BEGINNER_LEVEL,
                "English",
                "wel",
                "congrs",
                new BigDecimal(50000),
                Status.ACTIVE,
                categoryRepo.findCategoryByName("Testing"));

        course1.setCurrency("KZT");

        course1.setCreatedAt(LocalDateTime.now());
        course1.setUpdatedAt(LocalDateTime.now());

        course1.addTeacher(newUser);


        Course course2 = new Course("Java Spring",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "08829b86-7fb8-40fe-8f30-3b287bcd5a69.Java.png",
                null,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                CourseLevel.BEGINNER_LEVEL,
                "English",
                "wel",
                "congrs",
                new BigDecimal(30000),
                Status.ACTIVE,
                categoryRepo.findCategoryByName("Development"));

        course2.setCurrency("KZT");

        course2.setCreatedAt(LocalDateTime.now().minusDays(10));
        course2.setUpdatedAt(LocalDateTime.now().minusDays(10));
        course2.addTeacher(newUser);


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
                new Content("Course Outline", ContentType.VIDEO, section, courseCreated, "c5f75fb3-a641-4394-8243-825916747092.week11.mp4")
        );

        contentRepo.save(
                new Content("Are you getting it?", ContentType.DOWNLOAD, section, courseCreated, "d39255ed-ef58-4e37-907b-3c8311748979.example.txt")
        );

        contentRepo.save(
                new Content("Course Outline 1", ContentType.VIDEO, section1, courseCreated, "b2d60ae8-bae3-4615-9fc4-671cc70143c5.10week.mp4")
        );


    }
}

