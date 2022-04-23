package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ContentRepo extends JpaRepository<Content, UUID> {


    List<Content> findContentsByCourseId(UUID courseId);

//    @Modifying
//    @Query("update Content c set c.isCompleted=:isCompleted where c.id=:contentId")
//    void updateIsCompletedOfContent(UUID contentId, boolean isCompleted);

    @Query("SELECT c FROM Content c " +
            "JOIN c.students sc " +
            "WHERE sc.completed = true AND c.course.id=:courseId AND sc.student.id=:studentId")
    List<Content> getCompletedOfContentByStudentIdAndCourseId(UUID studentId, UUID courseId);
}
