package com.example.diplomaprojectgeneticcode.controller;

import com.example.diplomaprojectgeneticcode.dto.RegisterRequest;
import com.example.diplomaprojectgeneticcode.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/signUp")
public class SignUpController {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("")
    public String getSignUpPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "signup";
    }

    @PostMapping("")
    public ModelAndView createUser(@ModelAttribute RegisterRequest registerRequest) {

        log.info("Register request: {}", registerRequest);
        registerRequest.setSurname("surname");

        Response response = null;
        try {
            response = restTemplate.postForObject("/api/auth/signup", registerRequest, Response.class);
        }catch (Exception e) {
            log.error("Error in createUser: {}", e.getMessage());
        }


        Optional.ofNullable(response).filter(r -> r.getStatus().is2xxSuccessful()).orElseThrow();

        return new ModelAndView("redirect:/signIn");
    }
}
