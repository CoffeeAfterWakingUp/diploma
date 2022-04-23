package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.StudentContent;
import com.example.diplomaprojectgeneticcode.entity.StudentContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StudentContentRepo extends JpaRepository<StudentContent, StudentContentId> {

    @Query("SELECT sc FROM StudentContent sc " +
            "JOIN sc.content c " +
            "WHERE sc.student.id=:studentId AND sc.content.course.id=:courseId " +
            "ORDER BY c.orderNumber ASC, c.createdAt ASC")
    List<StudentContent> getStudentContentsByStudentAndContent(UUID studentId, UUID courseId);

}
