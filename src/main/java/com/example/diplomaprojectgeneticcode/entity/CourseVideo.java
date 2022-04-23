package com.example.diplomaprojectgeneticcode.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "course_video")
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"course"})
@AllArgsConstructor
@NoArgsConstructor
public class CourseVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;

    private String videoUrl;

    private String title;

    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    private Content content;







}
