package com.mk.ukim.finki.RecommendationSystem.model.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(int id) {
        super(String.format("Course with %d was not found!", id));
    }
}
