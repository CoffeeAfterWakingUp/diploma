package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.controller.util.PriceManager;
import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.controller.util.PriceManager.getRoundedPrice;
import static com.example.diplomaprojectgeneticcode.controller.util.PriceManager.getTotalPrice;
import static com.example.diplomaprojectgeneticcode.controller.util.SessionManager.getCartSession;
import static com.example.diplomaprojectgeneticcode.util.Constant.CART_PAGE;

@Controller
@RequestMapping("/cart")
@Slf4j
@RequiredArgsConstructor
public class CartController {

    @GetMapping
    public ModelAndView getCartPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(CART_PAGE);
        Set<CourseDTO> courses = getCartSession(session);
        BigDecimal totalPrice = getTotalPrice(courses);
        BigDecimal displayPrice = getRoundedPrice(totalPrice);


        modelAndView.addObject("courses", courses);
        modelAndView.addObject("coursesSize", courses.size());
        modelAndView.addObject("totalPrice", displayPrice);

        return modelAndView;
    }

    @GetMapping("/remove-course")
    public ModelAndView removeCourseFromCart(@RequestParam UUID courseId, HttpSession session) {
        Set<CourseDTO> coursesCart = getCartSession(session);
        removeCourse(coursesCart, courseId);

        return new ModelAndView("redirect:/cart");
    }


    private void removeCourse(Set<CourseDTO> courses, UUID courseId) {
        courses.removeIf(courseDTO -> courseDTO.getId().equals(courseId));
    }
}
