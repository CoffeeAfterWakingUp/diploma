package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.dto.CourseDto;
import com.example.diplomaprojectgeneticcode.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CourseRepo extends JpaRepository<Course, UUID> {



}
