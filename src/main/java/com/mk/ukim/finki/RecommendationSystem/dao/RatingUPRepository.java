package com.mk.ukim.finki.RecommendationSystem.dao;

import com.mk.ukim.finki.RecommendationSystem.entity.RatingUP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingUPRepository extends JpaRepository<RatingUP, Integer> {
}
