package com.mk.ukim.finki.RecommendationSystem.web.dto;

import com.mk.ukim.finki.RecommendationSystem.model.Course;

public class CourseRatingDto {

    public int courseId;
    public double rating;

    public CourseRatingDto(Course course, double rating) {
        this.courseId = course.getId();
        this.rating = rating;
    }
}
