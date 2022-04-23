package com.example.diplomaprojectgeneticcode.entity;

import com.example.diplomaprojectgeneticcode.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user_account")
@Getter
@Setter
//@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;
    private String name;
    private String surname;


    private String email;

    private String password;
    private String biography;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String image;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<VerificationToken> tokens = new ArrayList<>();

    private Boolean enabled;

    private Boolean locked;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "teachers", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CourseStudent> courseStudents = new ArrayList<>();

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StudentContent> contents = new ArrayList<>();

    public void addCourseToTeacher(Course course) {
        if(courses.contains(course)) {
            return;
        }
        courses.add(course);
    }

    public User(String name, String surname, String email, String password, String biography, Status status, String image, Boolean enabled, Boolean locked) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.status = status;
        this.image = image;
        this.enabled = enabled;
        this.locked = locked;
    }

    public String getFullName() {
        return this.getName() + " " + this.getSurname();
    }
}




