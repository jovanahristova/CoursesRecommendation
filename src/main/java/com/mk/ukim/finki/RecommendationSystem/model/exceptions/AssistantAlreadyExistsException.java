package com.mk.ukim.finki.RecommendationSystem.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class AssistantAlreadyExistsException extends RuntimeException {
    public AssistantAlreadyExistsException(int id) {
        super(String.format("Assistant with id %d already exists on given course!", id));
    }
}
