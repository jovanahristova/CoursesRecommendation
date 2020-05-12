package com.mk.ukim.finki.RecommendationSystem.web.controller;

import com.mk.ukim.finki.RecommendationSystem.model.StudyProgram;
import com.mk.ukim.finki.RecommendationSystem.service.StudyProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {


    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String userIndex() {
        return "home";
    }
}