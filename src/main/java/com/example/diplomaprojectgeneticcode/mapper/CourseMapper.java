package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.entity.CourseStudent;
import com.example.diplomaprojectgeneticcode.entity.Review;
import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.enums.Status;
import com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService;
import com.example.diplomaprojectgeneticcode.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class CourseMapper {

    private final ReviewService reviewService;


    public Course toCourse(CourseDTO courseDto) {
        log.info("CourseDTO: {}", courseDto);
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setTitle(courseDto.getTitle());
        course.setSubtitle(courseDto.getSubtitle());
        course.setImage(courseDto.getImage());
        course.setDescription(courseDto.getDescription());
        course.setBenefits(courseDto.getBenefits());
        course.setRequirements(courseDto.getRequirements());
        course.setCourseLevel(CourseLevel.valueOf(courseDto.getCourseLevel()));
        course.setCourseLang(courseDto.getCourseLang());
        course.setWelcomeMsg(courseDto.getWelcomeMsg());
        course.setCongratsMsg(courseDto.getCongratsMsg());
        course.setPrice(courseDto.getPrice());
        course.setStatus(Status.valueOf(courseDto.getStatus()));
        log.info("Course: {}", course);
        return course;
    }

    public CourseDTO toDto(Course course) {
        log.info("Course: {}", course);
        Optional<Course> courseOpt = Optional.ofNullable(course);
        CourseDTO courseDTO = new CourseDTO();


        courseOpt.map(Course::getId).ifPresent(courseDTO::setId);
        courseOpt.map(Course::getTitle).ifPresent(courseDTO::setTitle);
        courseOpt.map(Course::getSubtitle).ifPresent(courseDTO::setSubtitle);
        courseOpt.map(Course::getDescription).ifPresent(courseDTO::setDescription);
        courseOpt.map(Course::getImage).ifPresent(courseDTO::setImage);
        courseOpt.map(Course::getPromoVideoUrl).ifPresent(courseDTO::setPromoVideo);
        courseOpt.map(Course::getBenefits).ifPresent(courseDTO::setBenefits);
        courseOpt.map(Course::getRequirements).ifPresent(courseDTO::setRequirements);
        courseOpt.map(Course::getCourseLevel)
                .ifPresent(courseLevel -> courseDTO.setCourseLevel(courseLevel.toString()));
        courseOpt.map(Course::getCourseLang).ifPresent(courseDTO::setCourseLang);
        courseOpt.map(Course::getWelcomeMsg).ifPresent(courseDTO::setWelcomeMsg);
        courseOpt.map(Course::getCongratsMsg).ifPresent(courseDTO::setCongratsMsg);
        courseOpt.map(Course::getPrice).ifPresent(courseDTO::setPrice);
        courseOpt.map(Course::getStatus).ifPresent(status -> courseDTO.setStatus(status.toString()));
        courseOpt.map(Course::getCategory).ifPresent(category -> courseDTO.setCategory(category.getName()));
        courseOpt.map(Course::getCreatedAt).ifPresent(courseDTO::setCreatedAt);
        courseOpt.map(Course::getUpdatedAt).ifPresent(courseDTO::setUpdatedAt);
        courseOpt.map(Course::getRules).ifPresent(courseDTO::setRules);
        courseOpt.map(Course::getAbsenceLimit).ifPresent(courseDTO::setAbsenceLimit);
        courseOpt.map(Course::getCurrency).ifPresent(courseDTO::setCurrency);


        courseDTO.setReviewCount((int) reviewService.getCountOfReviewsOfCourse(courseOpt.map(Course::getId).orElse(null)));


        Double avgRating = Optional.ofNullable(reviewService.getAvgRatingOfCourse(courseOpt.map(Course::getId).orElse(null))).orElse(0.0);
        BigDecimal bd = BigDecimal.valueOf(avgRating);
        avgRating = bd.setScale(1, RoundingMode.HALF_UP).doubleValue();

        courseDTO.setRating(avgRating);

        courseDTO.setStudentCount((int) courseOpt
                .map(Course::getStudents)
                .stream()
                .count());


        Set<String> teachers = courseOpt.map(Course::getTeachers)
                .orElse(new HashSet<>())
                .stream()
                .map(User::getFullName)
                .collect(Collectors.toSet());

        Set<String> students = courseOpt.map(Course::getStudents)
                .orElse(Collections.emptyList())
                .stream()
                .map(CourseStudent::getStudent)
                .map(User::getFullName)
                .collect(Collectors.toSet());

        courseDTO.setTeachers(teachers);
        courseDTO.setStudents(students);

        log.info("CourseDTO: {}", courseDTO);

        return courseDTO;
    }


    public List<CourseDTO> toDto(List<Course> courses) {
        return Optional.ofNullable(courses)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
