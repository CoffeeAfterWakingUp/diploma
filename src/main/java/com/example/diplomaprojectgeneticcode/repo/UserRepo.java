package com.example.diplomaprojectgeneticcode.repo;

import com.example.diplomaprojectgeneticcode.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.enabled = TRUE " +
            "WHERE u.id = :id")
    void enableUser(UUID id);


    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.name=:name, " +
            "u.surname=:surname, " +
            "u.email=:email, " +
            "u.biography=:biography, " +
            "u.phoneNumber=:phoneNumber," +
            "u.personalWebsiteUrl=:personalWebsiteUrl," +
            "u.socialMediaUrl=:socialMediaUrl," +
            "u.image=:image " +
            "WHERE u.id=:id")
    void updateSettings(UUID id,
                        String name,
                        String surname,
                        String email,
                        String biography,
                        String phoneNumber,
                        String personalWebsiteUrl,
                        String socialMediaUrl,
                        String image);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.password=:newPassword " +
            "WHERE u.id=:id")
    void updatePassword(UUID id, String newPassword);


}
