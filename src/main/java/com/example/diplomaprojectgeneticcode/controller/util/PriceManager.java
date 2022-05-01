package com.example.diplomaprojectgeneticcode.controller.util;

import com.example.diplomaprojectgeneticcode.dto.CourseDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public class PriceManager {

    private PriceManager () {}


    public static BigDecimal getTotalPrice(Set<CourseDTO> courses) {
        if (courses == null) {
            return new BigDecimal(0);
        }
        return courses.stream()
                .map(CourseDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal getRoundedPrice(BigDecimal price) {
        return price.setScale(2, RoundingMode.HALF_EVEN);
    }
}
