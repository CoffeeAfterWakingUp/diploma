package com.example.diplomaprojectgeneticcode.service.interfaces;

import com.example.diplomaprojectgeneticcode.entity.VerificationToken;

import java.util.Optional;

public interface TokenService {
    VerificationToken getByToken(String token);
    void updateConfirmedAt(String token);
}
