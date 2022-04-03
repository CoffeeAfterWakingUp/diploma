package com.example.diplomaprojectgeneticcode.entity;

import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import com.example.diplomaprojectgeneticcode.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "course")
@Getter
@Setter
//@ToString
@EqualsAndHashCode(exclude = {"teachers"})
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @JsonIgnore
    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Content> contents = new ArrayList<>();


    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @JsonIgnore
    private Set<User> teachers = new HashSet<>();


    public void addTeacher(User teacher) {
        if(teachers.contains(teacher)) {
            return;
        }
        teachers.add(teacher);
    }

    public Course(String title, String subtitle, String description, String image, CourseVideo promoVideo, String benefits, String requirements, CourseLevel courseLevel, String courseLang, String welcomeMsg, String congratsMsg, BigDecimal price, Status status, Category category) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.image = image;
        this.promoVideo = promoVideo;
        this.benefits = benefits;
        this.requirements = requirements;
        this.courseLevel = courseLevel;
        this.courseLang = courseLang;
        this.welcomeMsg = welcomeMsg;
        this.congratsMsg = congratsMsg;
        this.price = price;
        this.status = status;
        this.category = category;
    }
}
