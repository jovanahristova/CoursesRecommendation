package com.mk.ukim.finki.RecommendationSystem.service.impl;

import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.RatingNotFoundException;
import com.mk.ukim.finki.RecommendationSystem.repository.RatingUPRepository;
import com.mk.ukim.finki.RecommendationSystem.model.RatingUP;
import com.mk.ukim.finki.RecommendationSystem.service.RatingUPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingUPServiceImpl implements RatingUPService {

    private RatingUPRepository ratingUPRepository;

    @Autowired
    public RatingUPServiceImpl( RatingUPRepository ratingUPRepository){
        this.ratingUPRepository = ratingUPRepository;
    }
    @Override
    public List<RatingUP> findAll() {
        return this.ratingUPRepository.findAll();
    }

    @Override
    public Page<RatingUP> findAll(int page, int size) {
        return this.ratingUPRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<RatingUP> findByUser(User user) {
        return this.ratingUPRepository.findByUser(user);
    }

    @Override
    public RatingUP findById(int id) {
        return this.ratingUPRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));

    }

    @Override
    public void save(RatingUP theRatingUP) {
       this.ratingUPRepository.save(theRatingUP);
    }

    @Override
    public void deleteById(int theId) {
        this.ratingUPRepository.deleteById(theId);
    }
}
