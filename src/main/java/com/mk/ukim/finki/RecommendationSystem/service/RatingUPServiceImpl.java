package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.dao.RatingUPRepository;
import com.mk.ukim.finki.RecommendationSystem.entity.RatingUP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingUPServiceImpl implements RatingUPService{

    private RatingUPRepository ratingUPRepository;

    @Autowired
    public RatingUPServiceImpl(RatingUPRepository ratingUPRepository){
        this.ratingUPRepository = ratingUPRepository;
    }
    @Override
    public List<RatingUP> findAll() {
        return ratingUPRepository.findAll();
    }

    @Override
    public RatingUP findById(int theId) {
        Optional<RatingUP> result = ratingUPRepository.findById(theId);

        RatingUP tempRatingUP = null;
        if(result.isPresent()){
            tempRatingUP = result.get();
        }
        return tempRatingUP;
    }

    @Override
    public void save(RatingUP theRatingUP) {
        ratingUPRepository.save(theRatingUP);
    }

    @Override
    public void deleteById(int theId) {
        ratingUPRepository.deleteById(theId);
    }
}
