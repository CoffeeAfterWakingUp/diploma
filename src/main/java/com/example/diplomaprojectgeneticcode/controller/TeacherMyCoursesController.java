package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.diplomaprojectgeneticcode.util.Constant.*;

@Controller
@RequestMapping("/t/my-courses")
@RequiredArgsConstructor
public class TeacherMyCoursesController {

    private final CourseService courseService;

    @ModelAttribute
    public void setAttributes(Model model) {
        model.addAttribute("page", "My Courses");
    }


    @GetMapping
    public ModelAndView getTeacherMyCoursesMyPage(HttpSession session,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size) {

        String username = (String) session.getAttribute(USER_SESSION);
        ModelAndView modelAndView = new ModelAndView(TEACHER_MY_COURSES_PAGE);
        //#TODO fix this
//        if(username == null) {
//            return new ModelAndView("redirect:/" + SIGN_IN_PAGE);
//        }

        List<CourseDTO> courses = courseService.getCoursesOfTeacher("180103273@stu.sdu.edu.kz");
        Page<CourseDTO> coursesPage = courseService.findPaginated(courses, PageRequest.of(page - 1, size));
        int totalPages = coursesPage.getTotalPages();
        modelAndView.addObject("coursesPage", coursesPage);
        modelAndView.addObject("pageNumbers", courseService.getPageNumbers(totalPages));
        modelAndView.addObject("paginationUrl", "/t/my-courses");

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchCoursesByTitle(HttpSession session,
                                             @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size,
                                             @RequestParam(defaultValue = "") String title) {

        String username = (String) session.getAttribute(USER_SESSION);
        ModelAndView modelAndView = new ModelAndView(TEACHER_MY_COURSES_PAGE);
//        if(username == null) {
//            return new ModelAndView("redirect:/" + SIGN_IN_PAGE);
//        }
        List<CourseDTO> courses = courseService.filterCoursesByTitle(title, courseService.getCoursesOfTeacher("180103273@stu.sdu.edu.kz"));
        Page<CourseDTO> coursesPage = courseService.findPaginated(courses, PageRequest.of(page - 1, size));
        int totalPages = coursesPage.getTotalPages();
        modelAndView.addObject("coursesPage", coursesPage);
        modelAndView.addObject("pageNumbers", courseService.getPageNumbers(totalPages));
        modelAndView.addObject("paginationUrl", "/t/my-courses/search");

        return modelAndView;
    }

}

