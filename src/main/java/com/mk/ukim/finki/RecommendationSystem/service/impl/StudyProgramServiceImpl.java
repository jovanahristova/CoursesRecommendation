package com.mk.ukim.finki.RecommendationSystem.service.impl;

import com.mk.ukim.finki.RecommendationSystem.model.exceptions.StudyProgramNotFoundException;
import com.mk.ukim.finki.RecommendationSystem.repository.StudyProgramRepository;
import com.mk.ukim.finki.RecommendationSystem.model.StudyProgram;
import com.mk.ukim.finki.RecommendationSystem.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    private StudyProgramRepository studyProgramRepository;

    @Autowired
    public StudyProgramServiceImpl( StudyProgramRepository studyProgramRepository){
        this.studyProgramRepository = studyProgramRepository;
    }


    @Override
    public List<StudyProgram> findAll() {
        return this.studyProgramRepository.findAll();
    }

    @Override
    public StudyProgram findById(int id) {
        return  this.studyProgramRepository.findById(id)
                .orElseThrow(() -> new StudyProgramNotFoundException(id));
    }


    @Override
    public void save(StudyProgram theStudyProgram) {
        this.studyProgramRepository.save(theStudyProgram);
    }

    @Override
    public void deleteById(int theId) {
        this.studyProgramRepository.deleteById(theId);
    }
}
