package com.mk.ukim.finki.RecommendationSystem.rest;

import com.mk.ukim.finki.RecommendationSystem.entity.RatingUC;
import com.mk.ukim.finki.RecommendationSystem.service.RatingUCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingUCRestController {
    private RatingUCService ratingUCService;

    @Autowired
    public RatingUCRestController(RatingUCService ratingUCService){
        this.ratingUCService = ratingUCService;
    }

    @GetMapping("/ratingsUC")
    public List<RatingUC> findAll() {
        return ratingUCService.findAll();
    }

    @GetMapping("/ratingsUC/{ratingUCId}")
    public RatingUC getRatingUC(@PathVariable int ratingUCId) {

        RatingUC theRatingUC = ratingUCService.findById(ratingUCId);

        if (theRatingUC == null) {
            throw new RuntimeException("RatingUC id not found - " + ratingUCId);
        }

        return theRatingUC;
    }

    @PostMapping("/ratingsUC")
    public RatingUC addRatingUC(@RequestBody RatingUC theRatingUC) {

        theRatingUC.setId(0);

        ratingUCService.save(theRatingUC);

        return theRatingUC;
    }

    @PutMapping("/ratingsUC")
    public RatingUC updateRatingUC(@RequestBody RatingUC theRatingUC) {

        ratingUCService.save(theRatingUC);

        return theRatingUC;
    }

    @DeleteMapping("/ratingsUC/{ratingUCId}")
    public String deleteRatingUC(@PathVariable int ratingUCId) {

        RatingUC tempRatingUC = ratingUCService.findById(ratingUCId);

        if (tempRatingUC == null) {
            throw new RuntimeException("RatingUCId id not found - " + ratingUCId);
        }

        ratingUCService.deleteById(ratingUCId);

        return "Deleted ratingUC id - " + ratingUCId;
    }
}
