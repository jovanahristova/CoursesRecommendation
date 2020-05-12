package com.mk.ukim.finki.RecommendationSystem.web.controller;

import javax.jws.WebParam;
import javax.validation.Valid;

import com.mk.ukim.finki.RecommendationSystem.model.StudyProgram;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.service.StudyProgramService;
import com.mk.ukim.finki.RecommendationSystem.service.UserService;
import com.mk.ukim.finki.RecommendationSystem.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    private final StudyProgramService studyProgramService;

    public UserRegistrationController(StudyProgramService studyProgramService) {
        this.studyProgramService = studyProgramService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        List<StudyProgram> studyPrograms = this.studyProgramService.findAll();
        model.addAttribute("studyPrograms", studyPrograms);

        String [] yearStudies = {"1", "2", "3", "4"};

        model.addAttribute("yearStudies", yearStudies);
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result, Model model){

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            List<StudyProgram> studyPrograms = this.studyProgramService.findAll();
            model.addAttribute("studyPrograms", studyPrograms);

            String [] yearStudies = {"1", "2", "3", "4"};

            model.addAttribute("yearStudies", yearStudies);
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/registration?success";
    }
}
