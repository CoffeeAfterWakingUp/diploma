package com.example.diplomaprojectgeneticcode.service.interfaces;


import com.example.diplomaprojectgeneticcode.dto.AuthenticationResponse;
import com.example.diplomaprojectgeneticcode.dto.LoginRequest;
import com.example.diplomaprojectgeneticcode.dto.RegisterRequest;

public interface AuthService {
    String signUp(RegisterRequest registerRequest);
    String confirmAccount(String token);
    String login(LoginRequest loginRequest);
}
