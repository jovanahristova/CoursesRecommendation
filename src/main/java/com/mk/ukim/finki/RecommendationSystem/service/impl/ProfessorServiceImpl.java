package com.mk.ukim.finki.RecommendationSystem.service.impl;

import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.ProfessorNotFoundException;
import com.mk.ukim.finki.RecommendationSystem.repository.ProfessorRepository;
import com.mk.ukim.finki.RecommendationSystem.service.ProfessorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> findAll() {
        return this.professorRepository.findAll();
    }

    @Override
    public Professor findById(int id) {
        return this.professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException(id));
    }



    @Override
    public Professor findProfessorByEmail(String email) {
        return this.professorRepository.findByEmail(email);
    }

    @Override
    public Professor createProfessor(Professor professor) {
        return this.professorRepository.save(professor);
    }

    @Override
    public Professor editProfessor(int id, Professor professor) {
        Professor p = this.findById(id);
        p.setFirstName(professor.getFirstName());
        p.setLastName(professor.getLastName());
        p.setTitle(professor.getTitle());
        p.setEmail(professor.getEmail());
        return this.professorRepository.save(p);
    }

    @Override
    public void deleteProfessorById(int id) {
        this.professorRepository.deleteById(id);
    }
}