package com.mk.ukim.finki.RecommendationSystem.repository;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
    public Professor findByEmail(String email);

    //List<Professor> findAllByCoursesByProffesor(Course course);

    //List<Professor> findAllByCoAndCoursesByAssitant(Course course);
}

