package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.Grade;
import com.example.diplomaprojectgeneticcode.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GradesRepo extends JpaRepository<Grade,Integer> {
    @Query("SELECT g FROM Grade g")
    List<Grade> getGrades();
}
