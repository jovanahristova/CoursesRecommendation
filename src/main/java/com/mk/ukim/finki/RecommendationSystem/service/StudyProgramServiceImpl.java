package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.dao.StudyProgramRepository;
import com.mk.ukim.finki.RecommendationSystem.entity.StudyProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramServiceImpl implements  StudyProgramService{

    private StudyProgramRepository studyProgramRepository;

    @Autowired
    public StudyProgramServiceImpl(StudyProgramRepository studyProgramRepository){
        this.studyProgramRepository = studyProgramRepository;
    }


    @Override
    public List<StudyProgram> findAll() {
        return studyProgramRepository.findAll();
    }

    @Override
    public StudyProgram findById(int theId) {
        Optional<StudyProgram> result = studyProgramRepository.findById(theId);

        StudyProgram tempStudyProgram = null;
        if(result.isPresent()){
            tempStudyProgram = result.get();
        }
        return tempStudyProgram;
    }

    @Override
    public void save(StudyProgram theStudyProgram) {
        studyProgramRepository.save(theStudyProgram);
    }

    @Override
    public void deleteById(int theId) {
        studyProgramRepository.deleteById(theId);
    }
}
