package com.example.diplomaprojectgeneticcode.entity;

import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;
    private String title;
    private String subtitle;
    private String description;
    private String image;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "promo_video", referencedColumnName = "id")
    private CourseVideo promoVideo;


    private String benefits;
    private String requirements;

    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;

    private String courseLang;
    private String welcomeMsg;
    private String congratsMsg;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    //private Category subcategory;


}
