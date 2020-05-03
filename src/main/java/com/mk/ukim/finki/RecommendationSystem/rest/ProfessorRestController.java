package com.mk.ukim.finki.RecommendationSystem.rest;

import com.mk.ukim.finki.RecommendationSystem.entity.Professor;
import com.mk.ukim.finki.RecommendationSystem.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfessorRestController {

    private ProfessorService professorService;

    @Autowired
    public ProfessorRestController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @GetMapping("/professors")
    public List<Professor> findAll() {
        return professorService.findAll();
    }

    @GetMapping("/professors/{professorId}")
    public Professor getProfessor(@PathVariable int professorId) {

        Professor theProfessor = professorService.findById(professorId);

        if (theProfessor == null) {
            throw new RuntimeException("Professor id not found - " + professorId);
        }

        return theProfessor;
    }

    @PostMapping("/professors")
    public Professor addProfessor(@RequestBody Professor theProfessor) {

        theProfessor.setId(0);

        professorService.save(theProfessor);

        return theProfessor;
    }

    @PutMapping("/professors")
    public Professor updateProfessor(@RequestBody Professor theProfessor) {

        professorService.save(theProfessor);

        return theProfessor;
    }

    @DeleteMapping("/professors/{professorId}")
    public String deleteProfessor(@PathVariable int professorId) {

        Professor tempProfessor = professorService.findById(professorId);

        if (tempProfessor == null) {
            throw new RuntimeException("Professor id not found - " + professorId);
        }

        professorService.deleteById(professorId);

        return "Deleted professor id - " + professorId;
    }
}
