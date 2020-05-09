package com.mk.ukim.finki.RecommendationSystem.service.impl;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.AssistantAlreadyExistsException;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.CourseNotFoundException;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.ProfessorAlreadyExistsException;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.ProfessorNotFoundException;
import com.mk.ukim.finki.RecommendationSystem.repository.CourseRepository;
import com.mk.ukim.finki.RecommendationSystem.repository.ProfessorRepository;
import com.mk.ukim.finki.RecommendationSystem.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private  final ProfessorRepository professorRepository;

    public CourseServiceImpl(CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Override
    public List<Course> findAllByTerm(char term) {
        return this.courseRepository.findAllByTerm(term);
    }

    @Override
    public Course findCourseByName(String name) {
        return this.courseRepository.findCourseByName(name);
    }

    @Override
    public Course createCourse(Course course) {
        List<Professor> professors = course.getProfessors();
        course.setProfessors(professors);

        List<Professor> assistants = course.getAssistants();
        course.setProfessors(assistants);
        return this.courseRepository.save(course);
    }

    @Override
    public Course editCourse(int id, Course course) {
//        Course c = this.findById(id);
//        c.setName(course.getName());
//        c.setDescription(course.getDescription());
//        c.setTerm(course.getTerm());
        course.setId(id);
        return this.courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        this.courseRepository.deleteById(id);
    }

    @Override
    public List<Professor> findProfesorsByCourse(int id) {
        Course course = this.findById(id);
        return course.getProfessors();
    }

    @Override
    public List<Professor> findAssistansByCourse(int id) {
        Course course = this.findById(id);
        return course.getAssistants();
    }

/*    @Override
    public Course saveProfessorAssistantToCourse(Course course) {
        Course c = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        Professor professor = this.professorRepository.findById(profId).orElseThrow(() -> new ProfessorNotFoundException(profId));
        Professor assistant = this.professorRepository.findById(assId).orElseThrow(() -> new ProfessorNotFoundException(assId));

        course.setProfessors(c.getProfessors());
        course.getProfessors().add(professor);
        course.setAssistants(c.getAssistants());
        course.getAssistants().add(assistant);
        return course;
    }

//    @Override
//    public Professor saveProfesorToCourse(int id, Professor professor) {
//        return null;
//    }*/

    @Override
    public Course saveProfessorAssistantToCourse(Course course, int profId, int assId) throws ProfessorAlreadyExistsException {
       // Course c = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        Professor professor = this.professorRepository.findById(profId).orElseThrow(() -> new ProfessorNotFoundException(profId));
        Professor assistant = this.professorRepository.findById(assId).orElseThrow(() -> new ProfessorNotFoundException(assId));

        List<Professor> professors = course.getProfessors();
        for(Professor p : professors){
            if(p.getId() == professor.getId())
                throw new ProfessorAlreadyExistsException(p.getId());
        }
        course.getProfessors().add(professor);
        List<Professor> assistants = course.getAssistants();
        for(Professor a : assistants){
            if(a.getId() == assistant.getId())
                throw new AssistantAlreadyExistsException(a.getId());
        }
        course.getAssistants().add(assistant);
        this.editCourse(course.getId(), course);
        return course;
    }


}
