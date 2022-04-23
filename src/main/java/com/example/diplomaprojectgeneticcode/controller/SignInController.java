package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.LoginRequest;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.example.diplomaprojectgeneticcode.util.Constant.*;

@Controller
@RequestMapping("signIn")
@Slf4j
public class SignInController {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public String getSignInPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return SIGN_IN_PAGE;
    }


    @PostMapping
    public ModelAndView loginUser(@ModelAttribute LoginRequest loginRequest, HttpSession session) {
        log.info("Login request: {}", loginRequest);
        ModelAndView modelAndView = new ModelAndView(SIGN_IN_PAGE);

        ResponseDTO responseDTO = null;
        try {
            responseDTO = restTemplate.postForObject("/api/auth/login", loginRequest, ResponseDTO.class);
        }catch (Exception e) {
            log.error("Error in loginUser: {}", e.getMessage());
            modelAndView.addObject("message", "Email is wrong!");
            return modelAndView;
        }

        if(Optional.ofNullable(responseDTO)
                .map(ResponseDTO::getData)
                .filter(d -> d.equals(SUCCESS))
                .isEmpty()){
            log.info("{}",Optional.ofNullable(responseDTO).map(ResponseDTO::getData).get());
            modelAndView.addObject("message", Optional.ofNullable(responseDTO).map(ResponseDTO::getData).get());
            return modelAndView;
        }


        session.setAttribute(USER_SESSION, loginRequest.getEmail());
        return new ModelAndView("redirect:/" + DASHBOARD_PAGE);
    }
}
