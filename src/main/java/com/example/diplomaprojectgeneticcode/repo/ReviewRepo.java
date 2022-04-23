package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepo extends JpaRepository<Review, UUID> {




}
