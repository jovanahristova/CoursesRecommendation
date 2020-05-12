package com.mk.ukim.finki.RecommendationSystem.repository;

import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import com.mk.ukim.finki.RecommendationSystem.model.RatingUP;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingUPRepository extends JpaRepository<RatingUP, Integer> {

    List<RatingUP> findByUser(User user);

    List<RatingUP> findAllByUserAndProfessor(User user, Professor professor);
}
