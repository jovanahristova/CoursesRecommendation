package com.mk.ukim.finki.RecommendationSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(){}

    public Role(ERole name){
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setRole(ERole name) {
        this.name = name;
    }
}
