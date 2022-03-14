package com.example.diplomaprojectgeneticcode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "course_video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;

    private String videoUrl;

    @OneToOne(mappedBy = "promoVideo")
    private Course course;



}
