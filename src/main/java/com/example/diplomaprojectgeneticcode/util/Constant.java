package com.example.diplomaprojectgeneticcode.util;

public final class Constant {

    private Constant() {}

    public static final String SUCCESS = "success";
    public static final String USER_NOT_FOUND_MSG = "User with email %s not found";
    public static final int BCRYPT_PASSWORD_STRENGTH = 10;
    public static final long TOKEN_EXPIRATION_MINUTES = 15;
    public static final String MESSAGE_ENCODING = "utf-8";
    public static final String EMAIL_SUBJECT = "Please Activate Your Account To Access QCRM";
    public static final String EMAIL_BODY = "" +
            "Thank you for signing up to QCRM, " +
            "please activate your account using below url: " +
            "http://localhost:8081/api/auth/confirm-account/%s";


}
