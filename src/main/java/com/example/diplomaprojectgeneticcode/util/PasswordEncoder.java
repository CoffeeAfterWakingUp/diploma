package com.example.diplomaprojectgeneticcode.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

/*
*  #TODO maybe delete this class, cause currently using other implementation
*
*
* */
public class PasswordEncoder {
    private static final int STRENGTH = 10;

    private PasswordEncoder() {}

    public static String encode(String plainPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        return bCryptPasswordEncoder.encode(plainPassword);
    }
}
