package com.example.diplomaprojectgeneticcode.mapper;

import com.example.diplomaprojectgeneticcode.dto.CourseDto;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.enums.Status;
import com.example.diplomaprojectgeneticcode.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CourseMapper {

    private final CategoryService categoryService;

    public static Course toCourse(CourseDto courseDto) {
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





        return course;

    }
}
