package com.example.diplomaprojectgeneticcode.enums;

public enum CourseLevel {
    BEGINNER_LEVEL("BEGINNER LEVEL"),
    INTERMEDIATE_LEVEL("INTERMEDIATE LEVEL"),
    EXPERT_LEVEL("EXPERT LEVEL"),
    ALL_LEVELS("ALL LEVELS");

    private final String level;

    CourseLevel(String level) {
        this.level = level;
    }
}
