package com.mk.ukim.finki.RecommendationSystem.service.impl;

import com.mk.ukim.finki.RecommendationSystem.model.exceptions.RatingNotFoundException;
import com.mk.ukim.finki.RecommendationSystem.repository.RatingUCRepository;
import com.mk.ukim.finki.RecommendationSystem.model.RatingUC;
import com.mk.ukim.finki.RecommendationSystem.service.RatingUCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingUCServiceImpl implements RatingUCService {

    private RatingUCRepository ratingUCRepository;

    @Autowired
    public RatingUCServiceImpl( RatingUCRepository ratingUCRepository){
        this.ratingUCRepository = ratingUCRepository;
    }
    @Override
    public List<RatingUC> findAll() {
        return this.ratingUCRepository.findAll();
    }

    @Override
    public RatingUC findById(int id) {
        return this.ratingUCRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));
    }

    @Override
    public void save(RatingUC theRatingUC) {
        this.ratingUCRepository.save(theRatingUC);
    }

    @Override
    public void deleteById(int theId) {
        this.ratingUCRepository.deleteById(theId);
    }
}
