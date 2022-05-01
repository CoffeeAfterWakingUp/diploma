package com.example.diplomaprojectgeneticcode.controller;


import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.ReviewDTO;
import com.example.diplomaprojectgeneticcode.dto.SectionDTO;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import com.example.diplomaprojectgeneticcode.service.client.ReviewService;
import com.example.diplomaprojectgeneticcode.service.client.SectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.controller.util.SessionManager.getCartSession;
import static com.example.diplomaprojectgeneticcode.controller.util.SessionManager.getUserSession;
import static com.example.diplomaprojectgeneticcode.util.Constant.CART_SESSION;
import static com.example.diplomaprojectgeneticcode.util.Constant.COURSE_PAGE;

@Controller
@RequestMapping("/courses")
@Slf4j
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final SectionService sectionService;
    private final ReviewService reviewService;

    @GetMapping("/{courseId}")
    public ModelAndView getCoursePage(@PathVariable UUID courseId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(COURSE_PAGE);
        CourseDTO course = courseService.getCourseById(courseId);
        List<SectionDTO> sections = sectionService.getSectionsByCourseId(courseId);
        List<ReviewDTO> reviews = reviewService.getReviewsByCourseId(courseId);

        String username = getUserSession(session);
        if(username == null) {
            return new ModelAndView("redirect:/signIn");
        }

        boolean studentRolledIn = courseService.isStudentRolledIn(username, courseId);
        getCartSession(session);


        modelAndView.addObject("course", course);
        modelAndView.addObject("sections", sections);
        modelAndView.addObject("reviews", reviews);
        modelAndView.addObject("studentRolledIn", studentRolledIn);

        return modelAndView;
    }

    @PostMapping("/cart")
    public ModelAndView addCourseToCart(@RequestParam UUID courseId, HttpSession session) {
        CourseDTO course = courseService.getCourseById(courseId);
        Set<CourseDTO> cart = getCartSession(session);
        cart.add(course);
        session.setAttribute(CART_SESSION, cart);


        return new ModelAndView("redirect:/" + String.format("courses/%s", courseId));
    }

}
