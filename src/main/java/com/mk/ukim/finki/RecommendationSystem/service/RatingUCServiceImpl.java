package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.dao.RatingUCRepository;
import com.mk.ukim.finki.RecommendationSystem.entity.RatingUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingUCServiceImpl implements RatingUCService{

    private RatingUCRepository ratingUCRepository;

    @Autowired
    public RatingUCServiceImpl(RatingUCRepository ratingUCRepository){
        this.ratingUCRepository = ratingUCRepository;
    }
    @Override
    public List<RatingUC> findAll() {
        return ratingUCRepository.findAll();
    }

    @Override
    public RatingUC findById(int theId) {
        Optional<RatingUC> result = ratingUCRepository.findById(theId);

        RatingUC tempRatingUC = null;
        if(result.isPresent()){
            tempRatingUC = result.get();
        }
        return tempRatingUC;
    }

    @Override
    public void save(RatingUC theRatingUC) {
        ratingUCRepository.save(theRatingUC);
    }

    @Override
    public void deleteById(int theId) {
        ratingUCRepository.deleteById(theId);
    }
}
