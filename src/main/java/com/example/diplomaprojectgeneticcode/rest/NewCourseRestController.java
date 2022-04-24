package com.example.diplomaprojectgeneticcode.rest;
import com.example.diplomaprojectgeneticcode.entity.Course;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.service.interfaces.CourseService;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(value = "/rest/newcourse")
public class NewCourseRestController {

    private final CourseService newCourseService;

    public NewCourseRestController(CourseService newCourseService){
        this.newCourseService = newCourseService;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "multipart/form-data")
    public Course createNewCourse(@RequestParam("courseName") String courseName,
                                  @RequestParam("courseDescription") String courseDescription,
                                  @RequestParam("coursePrice") int price) throws Exception {
        Course course = new Course();
        course.setTitle(courseName);
        course.setDescription(courseDescription);
        course.setPrice(BigDecimal.valueOf(price));
        course.setCreatedAt(new Jsr310JpaConverters.LocalDateTimeConverter().convertToEntityAttribute(new Date()));
        course.setImage("image");
        course.setCourseLang("ru");
        course.setCourseLevel(CourseLevel.EXPERT_LEVEL);
        return newCourseService.createCourse(course);
    }
}
