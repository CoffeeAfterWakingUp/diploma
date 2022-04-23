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
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "course")
@Getter
@Setter
//@ToString
@EqualsAndHashCode(exclude = {"teachers", "promoVideo", "students"})
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String subtitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String image;

    @Column(name = "promo_video")
    private String promoVideoUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String benefits;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String requirements;

    @Column(columnDefinition = "TEXT")
    private String rules;

    @Column(name = "absence_limit")
    private Integer absenceLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_level", nullable = false)
    private CourseLevel courseLevel;

    @Column(name = "course_lang", nullable = false)
    private String courseLang;

    @Column(name = "welcome_msg", columnDefinition = "TEXT")
    private String welcomeMsg;

    @Column(name = "congrats_msg", columnDefinition = "TEXT")
    private String congratsMsg;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CourseStudent> students = new ArrayList<>();


    public void addTeacher(User teacher) {
        if(teachers.contains(teacher)) {
            return;
        }
        teachers.add(teacher);
    }

    public void addStudent(User student) {
        CourseStudent courseStudent = new CourseStudent(this, student);
        students.add(courseStudent);
        student.getCourseStudents().add(courseStudent);
    }

    public Course(String title, String subtitle, String description, String image, String promoVideo, String benefits, String requirements, CourseLevel courseLevel, String courseLang, String welcomeMsg, String congratsMsg, BigDecimal price, Status status, Category category) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.image = image;
        this.promoVideoUrl = promoVideo;
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
