package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.web.dto.RecommendedCourseDto;

import java.util.List;

public interface RecommendationService {

    List<RecommendedCourseDto> getRecommendations (User user, char term, int limit);
}
