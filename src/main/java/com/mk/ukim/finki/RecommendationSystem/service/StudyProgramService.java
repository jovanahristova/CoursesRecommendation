package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.entity.StudyProgram;

import java.util.List;

public interface StudyProgramService {
    public List<StudyProgram> findAll();

    public StudyProgram findById(int theId);

    public void save(StudyProgram theStudyProgram);

    public void deleteById(int theId);
}
