package com.example.diplomaprojectgeneticcode.repo;


import com.example.diplomaprojectgeneticcode.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE VerificationToken t " +
            "SET t.confirmedAt = :confirmedAt " +
            "WHERE t.token = :token")
    void updateConfirmedAt(LocalDateTime confirmedAt, String token);

}
