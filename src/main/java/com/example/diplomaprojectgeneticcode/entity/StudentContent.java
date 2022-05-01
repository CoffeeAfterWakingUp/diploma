package com.example.diplomaprojectgeneticcode.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student_content")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StudentContent {

    @EmbeddedId
    private StudentContentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contentId")
    private Content content;

    private Boolean completed;

    private Integer grade;

    private String feedback;

    @Column(name = "calculated_weight")
    private Integer calculatedWeight;

    @Column(name = "min_range")
    private Integer minRange;

    @Column(name = "max_range")
    private Integer maxRange;

    private Integer percentage;

    private Boolean attended;

    private String teacherName;


    public StudentContent(StudentContentId id, User student, Content content, boolean isCompleted) {
        this.id = id;
        this.student = student;
        this.content = content;
        this.completed = isCompleted;
    }

    public StudentContent(User student, Content content) {
        this.id = new StudentContentId(student.getId(), content.getId());
        this.student = student;
        this.content = content;
        this.completed = false;
        this.attended = false;
    }
}
