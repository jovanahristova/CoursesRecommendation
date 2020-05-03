package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.dao.ProfessorRepository;
import com.mk.ukim.finki.RecommendationSystem.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional
    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    @Transactional
    public Professor findById(int theId) {
        Optional<Professor> result = professorRepository.findById(theId);

        Professor tempProfessor = null;
        if(result.isPresent()){
            tempProfessor = result.get();
        }
        return tempProfessor;
    }

    @Override
    @Transactional
    public void save(Professor theProfessor) {
        professorRepository.save(theProfessor);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        professorRepository.deleteById(theId);
    }
}