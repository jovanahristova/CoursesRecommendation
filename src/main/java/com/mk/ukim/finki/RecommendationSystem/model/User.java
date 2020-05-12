package com.mk.ukim.finki.RecommendationSystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "year_studies")
    private String yearStudies;

    @Column(name = "email")
    private String email;

    public User(){}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name="password")
    private String password;


    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "study_program_id")
    private StudyProgram studyProgram;

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

    public String getYearStudies() {
        return yearStudies;
    }

    public void setYearStudies(String yearStudies) {
        this.yearStudies = yearStudies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return Collections.singletonList(role);
    }
}
