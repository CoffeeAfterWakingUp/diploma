package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;
import com.example.diplomaprojectgeneticcode.dto.LoginRequest;
import com.example.diplomaprojectgeneticcode.dto.RegisterRequest;
import com.example.diplomaprojectgeneticcode.entity.*;
import com.example.diplomaprojectgeneticcode.enums.Status;
import com.example.diplomaprojectgeneticcode.repo.TokenRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.EmailSender;
import com.example.diplomaprojectgeneticcode.service.interfaces.TokenService;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.*;

class AuthServiceImplTest {
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserService userService;
    @Mock
    TokenRepo tokenRepo;
    @Mock
    EmailSender emailSender;
    @Mock
    TokenService tokenService;
    @Mock
    AuthenticationManager authenticationManager;
    @InjectMocks
    AuthServiceImpl authServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin() {
        String s = "a";
        User user2 = new User();
        User user = null;
        Optional<User> user1 = Optional.ofNullable(user);
        user1.map(User::getEmail).ifPresent(s1 -> user2.setEmail(s));
        System.out.println(user2.getEmail());

        Optional<CourseDTO> build = Optional.ofNullable(CourseDTO.builder().courseLang(null).build());
        System.out.println(build.get());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme