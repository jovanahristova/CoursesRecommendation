package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.model.RatingUP;

import java.util.List;

public interface RatingUPService {

    public List<RatingUP> findAll();

    public RatingUP findById(int id);

    public void save(RatingUP theRatingUP);

    public void deleteById(int id);
}
