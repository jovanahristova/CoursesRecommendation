package com.mk.ukim.finki.RecommendationSystem.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class RoleIdNotFoundException extends RuntimeException {
    public RoleIdNotFoundException(int id) {
        super(String.format("Role with %d was not found!", id));
    }
}
