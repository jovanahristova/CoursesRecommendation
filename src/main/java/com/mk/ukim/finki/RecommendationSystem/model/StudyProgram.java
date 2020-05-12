package com.mk.ukim.finki.RecommendationSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "study_program")
public class StudyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "code")
    private String code;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyProgram that = (StudyProgram) o;
        return Id == that.Id &&
                code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, code);
    }
}
