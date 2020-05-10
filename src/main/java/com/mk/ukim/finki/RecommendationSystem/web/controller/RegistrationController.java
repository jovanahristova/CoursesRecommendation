package com.mk.ukim.finki.RecommendationSystem.web.controller;

import com.mk.ukim.finki.RecommendationSystem.model.Role;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.service.RoleService;
import com.mk.ukim.finki.RecommendationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import java.util.logging.Logger;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    private final RoleService roleService;

    private Logger logger = Logger.getLogger(getClass().getName());

    public RegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/registerNewUser")
    public String userSignUp(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/test")
    public  String test(@Valid User u){
        return "jovana";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("user") User user,
            BindingResult theBindingResult,
            Model model) {

        String username = user.getUsername();
        logger.info("Processing registration form for: " + username);

        // form validation
        if (theBindingResult.hasErrors()) {
            return "register";
        }

        // check the database if user already exists
        User existing = this.userService.findByUsername(username);
        if (existing != null) {
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "register";
        }
        // create user account
        Role role = this.roleService.getRoleById(2);
        user.setRole(role);
        userService.create(user);

        logger.info("Successfully created user: " + username);

        return "registration-confirmation";
    }


}