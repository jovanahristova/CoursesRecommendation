package com.mk.ukim.finki.RecommendationSystem.web.controller;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import com.mk.ukim.finki.RecommendationSystem.service.CourseService;
import com.mk.ukim.finki.RecommendationSystem.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/professors")
public class ProfessorController {

    private final ProfessorService professorService;
    private final CourseService courseService;

    @Autowired
    public ProfessorController(ProfessorService professorService, CourseService courseService){
        this.professorService = professorService;
        this.courseService = courseService;
    }

    @GetMapping
    public String getProfessorPage(Model model){
        List<Professor> professors = this.professorService.findAll();
        model.addAttribute("professors", professors);
        return "professors";
    }

    @GetMapping("/add-new")
    public String addNewProfessorPage(Model model){
        model.addAttribute("professor", new Professor());
        return  "add-professor";
    }

    @GetMapping("/{id}/edit")
    public String editProfessorPage(@PathVariable int id, Model model){
        try {
            Professor professor = this.professorService.findById(id);
            model.addAttribute("professor", professor);
            return "add-professor";

        }catch (RuntimeException ex){
            return "redirect:/professors?error=" + ex.getLocalizedMessage();
        }
    }

    @GetMapping("/{id}/coursesAsProfessor")
    public String coursesAsProfessor(@PathVariable int id, Model model){
        Professor professor = this.professorService.findById(id);
        model.addAttribute("professor", professor);

        List<Course> courses = professor.getCoursesByProffesor();
        model.addAttribute("courses", courses);

        return "coursesAsProfessor";
    }

    @GetMapping("/{id}/coursesAsAssistant")
    public String coursesAsAssistant(@PathVariable int id, Model model){

        Professor assistant = this.professorService.findById(id);
        model.addAttribute("assistant", assistant);

        List<Course> courses = assistant.getCoursesByAssitant();
        model.addAttribute("courses", courses);
        return "coursesAsAssistant";
    }

    @PostMapping
    public String saveProfessor(@Valid Professor professor,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()) {
            return "add-professor";
        }
        this.professorService.createProfessor(professor);
        return "redirect:/professors";
    }

    @PostMapping("/{id}/delete")
    public String deleteProfessor(@PathVariable int id){
        this.professorService.deleteProfessorById(id);

        return  "redirect:/professors";
    }

    @PostMapping("/{id}/deleteCourseA/{idC}")
    public String deleteCourseA(@PathVariable int id, @PathVariable int idC){
        Professor assistant = this.professorService.findById(id);
        Course course = this.courseService.findById(idC);

        List<Course> lista = assistant.getCoursesByAssitant();
        assistant.getCoursesByAssitant().remove(course);

        this.professorService.editProfessor(assistant.getId(), assistant);
        return  "redirect:/professors";
    }

    @PostMapping("/{id}/deleteCourseP/{idC}")
    public String deleteCourseP(@PathVariable int id, @PathVariable int idC){
        Professor professor = this.professorService.findById(id);
        Course course = this.courseService.findById(idC);

        professor.getCoursesByProffesor().remove(course);

        this.professorService.editProfessor(professor.getId(), professor);
        return  "redirect:/professors";
    }
}
