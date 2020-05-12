package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.model.RatingUC;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RatingUCService {
    public List<RatingUC> findAll();

    Page<RatingUC> findAll(int page, int size);

    Page<RatingUC> findAllByUser(User user, int page, int size);

    public RatingUC findById(int id);

    public List<RatingUC> findByUser(User user);

    public void save(RatingUC theRatingUC);

    public void deleteById(int id);
}
