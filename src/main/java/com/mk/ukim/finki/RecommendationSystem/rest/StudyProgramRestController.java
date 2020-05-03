package com.mk.ukim.finki.RecommendationSystem.rest;


import com.mk.ukim.finki.RecommendationSystem.entity.StudyProgram;
import com.mk.ukim.finki.RecommendationSystem.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyProgramRestController {

    private StudyProgramService studyProgramService;

    @Autowired
    public StudyProgramRestController(StudyProgramService studyProgramService){

        this.studyProgramService = studyProgramService;
    }

    @GetMapping("/studyPrograms")
    public List<StudyProgram> findAll() {
        return studyProgramService.findAll();
    }

    @GetMapping("/studyPrograms/{studyProgramId}")
    public StudyProgram getStudyProgram(@PathVariable int studyProgramId) {

        StudyProgram theStudyProgram = studyProgramService.findById(studyProgramId);

        if (theStudyProgram == null) {
            throw new RuntimeException("Study program id not found - " + studyProgramId);
        }

        return theStudyProgram;
    }

    @PostMapping("/studyPrograms")
    public StudyProgram addStudyProgram(@RequestBody StudyProgram theStudyProgram) {

        theStudyProgram.setId(0);

        studyProgramService.save(theStudyProgram);

        return theStudyProgram;
    }

    @PutMapping("/studyPrograms")
    public StudyProgram updateStudyProgram(@RequestBody StudyProgram theStudyProgram) {

        studyProgramService.save(theStudyProgram);

        return theStudyProgram;
    }

    @DeleteMapping("/studyPrograms/{studyProgramId}")
    public String deleteStudyProgram(@PathVariable int studyProgramId) {

        StudyProgram theStudyProgram = studyProgramService.findById(studyProgramId);

        if (theStudyProgram == null) {
            throw new RuntimeException("Study program id not found - " + studyProgramId);
        }

        studyProgramService.deleteById(studyProgramId);

        return "Deleted study program id - " + studyProgramId;
    }
}