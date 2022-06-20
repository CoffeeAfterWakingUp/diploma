package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SectionRepo extends JpaRepository<Section, UUID> {


    @Query("SELECT s FROM Section s " +
            "LEFT JOIN s.contents c " +
            "WHERE c.course.id=:courseId " +
            "GROUP BY s.id " +
            "ORDER BY s.orderNumber ASC")
    List<Section> getSectionsByCourseId(UUID courseId);






}
