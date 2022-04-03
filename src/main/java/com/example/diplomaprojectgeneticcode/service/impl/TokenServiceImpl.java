package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.entity.VerificationToken;
import com.example.diplomaprojectgeneticcode.repo.TokenRepo;
import com.example.diplomaprojectgeneticcode.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepo tokenRepo;

    @Override
    public VerificationToken getByToken(String token) {
        if(token == null) {
            log.error("Token must not be null");
            throw new IllegalArgumentException("Token must not be null");
        }
        Optional<VerificationToken> verificationToken = tokenRepo.findByToken(token);
        return verificationToken.orElseThrow();
    }

    @Override
    public void updateConfirmedAt(String token) {
        tokenRepo.updateConfirmedAt(LocalDateTime.now(), token);
    }
}
