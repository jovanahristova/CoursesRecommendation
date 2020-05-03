package com.mk.ukim.finki.RecommendationSystem.dao;

import com.mk.ukim.finki.RecommendationSystem.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
