package com.example.diplomaprojectgeneticcode.enums;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum CourseLevel {
    BEGINNER_LEVEL("BEGINNER_LEVEL"),
    INTERMEDIATE_LEVEL("INTERMEDIATE_LEVEL"),
    EXPERT_LEVEL("EXPERT_LEVEL"),
    ALL_LEVELS("ALL_LEVELS");

    private final String level;

    CourseLevel(String level) {
        this.level = level;
    }

    private static final Set<String> levels = EnumSet.allOf(CourseLevel.class)
            .stream()
            .map(CourseLevel::getLevel).collect(Collectors.toSet());

    public static Set<String> levels() {
        return levels;
    }
}
