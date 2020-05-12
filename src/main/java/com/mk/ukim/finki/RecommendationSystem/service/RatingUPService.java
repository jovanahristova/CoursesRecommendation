package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.model.RatingUP;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RatingUPService {

    public List<RatingUP> findAll();

    Page<RatingUP> findAll(int page, int size);

    public List<RatingUP> findByUser(User user);

    public RatingUP findById(int id);

    public void save(RatingUP theRatingUP);

    public void deleteById(int id);
}
