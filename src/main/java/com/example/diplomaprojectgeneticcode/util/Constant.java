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
    public static final String USER_SESSION = "currentUser";

    public static final String SIGN_IN_PAGE = "signIn";
    public static final String SIGN_UP_PAGE = "signUp";
    public static final String DASHBOARD_PAGE = "dashboard";
    public static final String COURSE_REG_PAGE = "сourseRegistration";
    public static final String COURSE_CURRICULUM_PAGE = "courseCurriculum";
    public static final String COURSE_CURRICULUM_DASHBOARD_PAGE = "courseCurriculumDashboard";
    public static final String STUDENT_GRADES_PAGE = "StudentGrades";
    public static final String STUDENT_ATTENDANCE_PAGE = "StudentAttendance";
    public static final String STUDENT_MY_COURSES_PAGE = "StudentMyCourses";
    public static final String STUDENT_MY_COURSE_PAGE = "TeacherMycourse";
    public static final String NEW_COURSE_PAGE = "NewCourse";
    public static final String MY_PROFILE_PAGE = "Myprofile";
    public static final String GRADES_PAGE = "Grades";
    public static final String TEACHER_DASHBOARD_PAGE = "TeacherDashboard";
    public static final String FAQ_PAGE = "FAQ";
    public static final String NEWS_PAGE = "news";


}
