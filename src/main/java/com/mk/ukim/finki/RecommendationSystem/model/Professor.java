package com.mk.ukim.finki.RecommendationSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "title")
    private String title;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinTable(
            name = "course_professor",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private List<Course> coursesByProffesor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinTable(
            name = "course_assistant",
            joinColumns = @JoinColumn(name = "assistant_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private List<Course> coursesByAssitant;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCoursesByProffesor() {
        return coursesByProffesor;
    }

    public void setCoursesByProffesor(List<Course> coursesByProffesor) {
        this.coursesByProffesor = coursesByProffesor;
    }

    public List<Course> getCoursesByAssitant() {
        return coursesByAssitant;
    }

    public void setCoursesByAssitant(List<Course> coursesByAssitant) {
        this.coursesByAssitant = coursesByAssitant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstAndLastName(){
        return String.format("%s %s", this.firstName, this.lastName);
    }

    @Override
    public String toString() {
        return "";
    }
}

