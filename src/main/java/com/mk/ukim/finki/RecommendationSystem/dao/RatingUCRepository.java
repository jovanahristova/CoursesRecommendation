package com.mk.ukim.finki.RecommendationSystem.dao;

import com.mk.ukim.finki.RecommendationSystem.entity.RatingUC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingUCRepository extends JpaRepository<RatingUC, Integer> {
}
