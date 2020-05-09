package com.mk.ukim.finki.RecommendationSystem.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StudyProgramNotFoundException extends RuntimeException {
    public StudyProgramNotFoundException(int id) {
        super(String.format("StudyProgram with id %d was not found!", id));
    }
}
