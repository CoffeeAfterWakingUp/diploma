package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.CourseStudent;
import com.example.diplomaprojectgeneticcode.entity.CourseStudentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStudentRepo extends JpaRepository<CourseStudent, CourseStudentId> {
}
