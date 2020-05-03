package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.entity.Professor;

import java.util.List;

public interface ProfessorService {
    public List<Professor> findAll();

    public Professor findById(int theId);

    public void save(Professor theProfessor);

    public void deleteById(int theId);
}
