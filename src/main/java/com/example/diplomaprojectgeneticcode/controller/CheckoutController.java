package com.example.diplomaprojectgeneticcode.controller;


import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Set;

import static com.example.diplomaprojectgeneticcode.controller.util.PriceManager.getRoundedPrice;
import static com.example.diplomaprojectgeneticcode.controller.util.PriceManager.getTotalPrice;
import static com.example.diplomaprojectgeneticcode.controller.util.SessionManager.*;
import static com.example.diplomaprojectgeneticcode.util.Constant.CHECKOUT_PAGE;

@Controller
@RequestMapping("/cart")
@Slf4j
@RequiredArgsConstructor
public class CheckoutController {

    @GetMapping("/checkout")
    public ModelAndView getCheckoutPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(CHECKOUT_PAGE);
        Set<CourseDTO> courses = getCartSession(session);
        BigDecimal totalPrice = getTotalPrice(courses);
        BigDecimal displayPrice = getRoundedPrice(totalPrice);


        modelAndView.addObject("courses", courses);
        modelAndView.addObject("totalPrice", displayPrice);

        return modelAndView;
    }

    @PostMapping("/checkout/complete-payment")
    public ModelAndView completePayment(HttpSession session) {
        String username = getUserSession(session);

        if(username == null) {
            return new ModelAndView("redirect:/signIn");
        }

        return new ModelAndView("redirect:/student-course/rollIn" + String.format("?username=%s", username));

    }
}
