package com.mk.ukim.finki.RecommendationSystem.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class ProfessorNotFoundException extends RuntimeException {
    public ProfessorNotFoundException(int id) {
        super(String.format("Professor with id %d was not found!", id));
    }
}
