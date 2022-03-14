package com.example.diplomaprojectgeneticcode.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class PasswordEncoder {
    private static final int STRENGTH = 10;

    private PasswordEncoder() {}

    public static String encode(String plainPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        return bCryptPasswordEncoder.encode(plainPassword);
    }
}
