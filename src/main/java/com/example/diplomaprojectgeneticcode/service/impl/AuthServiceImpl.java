package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.dto.Email;
import com.example.diplomaprojectgeneticcode.dto.LoginRequest;
import com.example.diplomaprojectgeneticcode.dto.RegisterRequest;
import com.example.diplomaprojectgeneticcode.entity.User;
import com.example.diplomaprojectgeneticcode.entity.VerificationToken;
import com.example.diplomaprojectgeneticcode.enums.Status;
import com.example.diplomaprojectgeneticcode.repo.TokenRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.EmailSender;
import com.example.diplomaprojectgeneticcode.service.interfaces.AuthService;
import com.example.diplomaprojectgeneticcode.service.interfaces.TokenService;
import com.example.diplomaprojectgeneticcode.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.diplomaprojectgeneticcode.util.Constant.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TokenRepo tokenRepo;
    private final EmailSender emailSender;
    //private final JwtProvider jwtProvider;

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;


    @Override
    @Transactional
    public String signUp(RegisterRequest registerRequest) {

        /* #TODO
              Check email, name, surname, password
        * */

        User user = User.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .enabled(false)
                .locked(false)
                .status(Status.DISABLED)
                .build();
        userService.create(user);
        String token = this.generateVerificationToken(user);
        emailSender.send(Email.builder()
                .body(String.format(EMAIL_BODY,token))
                .recipient(user.getEmail())
                .subject(EMAIL_SUBJECT)
                .build());

        return "User is created!";
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .createdAt(now)
                .expiresAt(now.plusMinutes(TOKEN_EXPIRATION_MINUTES))
                .build();
        tokenRepo.save(verificationToken);
        return token;
    }

    @Override
    @Transactional
    public String confirmAccount(String token) {
        VerificationToken verificationToken = tokenService.getByToken(token);
        LocalDateTime tokenExpiresAt = verificationToken.getExpiresAt();
        LocalDateTime now = LocalDateTime.now();
        if(verificationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        if(tokenExpiresAt.isBefore(now)) {
            throw new IllegalStateException("token expired");
        }

        tokenService.updateConfirmedAt(token);
        userService.enableUser(verificationToken.getUser().getId());

        return "Account is confirmed!";
    }

    @Override
    public String login(LoginRequest loginRequest) {
        String promptedEmail = loginRequest.getEmail();
        String promptedPassword = loginRequest.getPassword();

        if(promptedEmail == null || promptedEmail.isBlank()) {
            return "Email is wrong";
        }
        if(promptedPassword == null || promptedPassword.isBlank()) {
            return "Password is wrong";
        }

        User user = userService.getUserByEmail(promptedEmail);

        if(user == null) {
            return "Email is wrong";
        }
        if(!passwordEncoder.matches(promptedPassword, user.getPassword())) {
            return "Password is wrong";
        }

        if(Boolean.FALSE.equals(user.getEnabled())) {
            return "User is not enabled";
        }
        if(Boolean.TRUE.equals(user.getLocked())) {
            return "User is locked";
        }

        return "success";
    }
}
