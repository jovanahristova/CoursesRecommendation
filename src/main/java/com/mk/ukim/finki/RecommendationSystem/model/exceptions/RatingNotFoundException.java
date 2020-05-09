package com.mk.ukim.finki.RecommendationSystem.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(int id) {
        super(String.format("Rating with id %d was not found!", id));
    }
}
