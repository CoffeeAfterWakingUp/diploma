package com.example.diplomaprojectgeneticcode.rest;

import com.example.diplomaprojectgeneticcode.dto.LoginRequest;
import com.example.diplomaprojectgeneticcode.dto.RegisterRequest;
import com.example.diplomaprojectgeneticcode.http.Response;
import com.example.diplomaprojectgeneticcode.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.diplomaprojectgeneticcode.util.Constant.SUCCESS;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthRestController {

    private final AuthService authService;

    @PostMapping(value = "/signup", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .message(SUCCESS)
                        .data(authService.signUp(registerRequest))
                        .build()
        );
    }


    @GetMapping(value = "/confirm-account/{token}")
    public ResponseEntity<Response<?>> confirmAccount(@PathVariable String token) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(authService.confirmAccount(token))
                        .build()
        );
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Response<?>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(authService.login(loginRequest))
                        .build()
        );
    }







}
