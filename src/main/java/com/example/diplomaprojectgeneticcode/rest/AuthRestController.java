package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.LoginRequest;
import com.example.diplomaprojectgeneticcode.dto.RegisterRequest;
import com.example.diplomaprojectgeneticcode.dto.ResponseDTO;
import com.example.diplomaprojectgeneticcode.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthRestController extends AbstractRestController {

    private final AuthService authService;

    @PostMapping(value = "/signup", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<String>> register(@RequestBody RegisterRequest registerRequest) {
        return success(authService.signUp(registerRequest), CREATED);
    }


    @GetMapping(value = "/confirm-account/{token}")
    public ResponseEntity<ResponseDTO<String>> confirmAccount(@PathVariable String token) {
        return successOK(authService.confirmAccount(token));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO<String>> login(@RequestBody LoginRequest loginRequest) {
        return successOK(authService.login(loginRequest));
    }


}
