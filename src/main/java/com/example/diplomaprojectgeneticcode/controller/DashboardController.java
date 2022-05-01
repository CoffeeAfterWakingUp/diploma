package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.service.client.CategoryService;
import com.example.diplomaprojectgeneticcode.service.client.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.example.diplomaprojectgeneticcode.util.Constant.*;

@Controller
@RequestMapping("/dashboard")
@Slf4j
public class DashboardController {

    private CourseService courseService;
    private CategoryService categoryService;

    @Autowired
    public void setCourseService(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("topCourses", courseService.findPaginated(courseService.getTopCourses(), PageRequest.of(0, 9)));
        model.addAttribute("popularCategories", categoryService.findPaginated(categoryService.getPopularCategories(), PageRequest.of(0, 9)));

    }

    @GetMapping
    public ModelAndView getDashboardPage(HttpSession session,
                                         @RequestParam(required = false) boolean completePaymentSuccess) {
        String userSession = (String) session.getAttribute(USER_SESSION);
        ModelAndView modelAndView = new ModelAndView(DASHBOARD_PAGE);

//        if (userSession == null) {
//            modelAndView = new ModelAndView("redirect:/" + SIGN_IN_PAGE);
//            return modelAndView;
//        }

        return modelAndView;
    }
}
