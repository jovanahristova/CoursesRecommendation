package com.mk.ukim.finki.RecommendationSystem.repository;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    //za da mi vraka predmeti od daden semestar
    public List<Course> findAllByTerm(char term);

   public Course findCourseByName(String name);

    //List<Course> findAllByProfessors
}
