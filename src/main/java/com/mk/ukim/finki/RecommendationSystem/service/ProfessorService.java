package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<Professor> findAll();

    Page<Professor> findAll(int page, int size);

    //List<Professor> findAllByCoursesByProffesor(Course course);

    Professor findById(int id);

    Professor findProfessorByEmail(String email);

    //Page<Professor> searchProfessor(String email,int page,int size);

    Professor createProfessor(Professor professor);

    Professor editProfessor(int id, Professor professor);

    void deleteProfessorById(int id);
}
