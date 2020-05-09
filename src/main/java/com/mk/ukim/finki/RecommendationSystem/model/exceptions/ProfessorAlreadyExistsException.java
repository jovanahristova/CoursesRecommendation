package com.mk.ukim.finki.RecommendationSystem.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ProfessorAlreadyExistsException extends Exception {
    public ProfessorAlreadyExistsException(int id) {
        super(String.format("Professor with id %d already exists on given course!", id));
    }
}
