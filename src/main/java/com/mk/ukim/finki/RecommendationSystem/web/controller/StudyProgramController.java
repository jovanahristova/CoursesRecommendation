package com.mk.ukim.finki.RecommendationSystem.web.controller;


import com.mk.ukim.finki.RecommendationSystem.model.StudyProgram;
import com.mk.ukim.finki.RecommendationSystem.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/studyprograms")
public class StudyProgramController {

    private StudyProgramService studyProgramService;

    @Autowired
    public StudyProgramController(StudyProgramService studyProgramService){

        this.studyProgramService = studyProgramService;
    }

    @GetMapping
    public String getStudyProgramPage(Model model){
        List<StudyProgram> studyPrograms = this.studyProgramService.findAll();
        model.addAttribute("studyPrograms", studyPrograms);
        return "studyprograms";
    }

    @GetMapping("/add-new")
    public String addNewStudyProgramPage(Model model){
        model.addAttribute("studyProgram", new StudyProgram());
        return  "add-studyProgram";
    }

    @GetMapping("/{id}/edit")
    public String editStudyProgramPage(@PathVariable int id, Model model){
        try {
            StudyProgram studyProgram = this.studyProgramService.findById(id);
            model.addAttribute("studyProgram", studyProgram);
            return "add-studyProgram";

        }catch (RuntimeException ex){
            return "redirect:/studyprograms?error=" + ex.getLocalizedMessage();
        }
    }

    @PostMapping
    public String saveStudyProgram(@Valid StudyProgram studyProgram,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()) {
            return "add-studyProgram";
        }
        this.studyProgramService.save(studyProgram);
        return "redirect:/studyprograms";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudyProgram(@PathVariable int id){
        this.studyProgramService.deleteById(id);

        return  "redirect:/studyprograms";
    }

}