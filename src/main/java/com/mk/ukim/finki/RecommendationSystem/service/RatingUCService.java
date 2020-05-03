package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.entity.RatingUC;

import java.util.List;

public interface RatingUCService {
    public List<RatingUC> findAll();

    public RatingUC findById(int theId);

    public void save(RatingUC theRatingUC);

    public void deleteById(int theId);
}
