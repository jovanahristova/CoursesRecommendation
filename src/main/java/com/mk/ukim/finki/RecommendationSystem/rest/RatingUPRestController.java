package com.mk.ukim.finki.RecommendationSystem.rest;

import com.mk.ukim.finki.RecommendationSystem.entity.RatingUC;
import com.mk.ukim.finki.RecommendationSystem.entity.RatingUP;
import com.mk.ukim.finki.RecommendationSystem.service.RatingUCService;
import com.mk.ukim.finki.RecommendationSystem.service.RatingUPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingUPRestController {
    private RatingUPService ratingUPService;

    @Autowired
    public RatingUPRestController(RatingUPService ratingUPService){
        this.ratingUPService = ratingUPService;
    }

    @GetMapping("/ratingsUP")
    public List<RatingUP> findAll() {
        return ratingUPService.findAll();
    }

    @GetMapping("/ratingsUP/{ratingUPId}")
    public RatingUP getRatingUP(@PathVariable int ratingUPId) {

        RatingUP theRatingUP = ratingUPService.findById(ratingUPId);

        if (theRatingUP == null) {
            throw new RuntimeException("RatingUP id not found - " + ratingUPId);
        }

        return theRatingUP;
    }

    @PostMapping("/ratingsUP")
    public RatingUP addRatingUP(@RequestBody RatingUP theRatingUP) {

        theRatingUP.setId(0);

        ratingUPService.save(theRatingUP);

        return theRatingUP;
    }

    @PutMapping("/ratingsUP")
    public RatingUP updateRatingUP(@RequestBody RatingUP theRatingUP) {

        ratingUPService.save(theRatingUP);

        return theRatingUP;
    }

    @DeleteMapping("/ratingsUP/{ratingUPId}")
    public String deleteRatingUP(@PathVariable int ratingUPId) {

        RatingUP tempRatingUP = ratingUPService.findById(ratingUPId);

        if (tempRatingUP == null) {
            throw new RuntimeException("RatingUP id not found - " + ratingUPId);
        }

        ratingUPService.deleteById(ratingUPId);

        return "Deleted ratingUP id - " + ratingUPId;
    }
}
