package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.ProfessorAlreadyExistsException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    //Page<Course> findAll(int page, int size);

    Course findById(int id);

    List<Course> findAllByTerm(char term);

    Course findCourseByName(String name);

   // Page<Course> searchCourse(String term,int page,int size);

    Course createCourse(Course course);

    Course editCourse(int id, Course course);

    void deleteCourseById(int id);

    List<Professor> findProfesorsByCourse(int id);

    List<Professor> findAssistansByCourse(int id);

   Course saveProfessorAssistantToCourse(Course course, int profId, int assId) throws ProfessorAlreadyExistsException;

}
