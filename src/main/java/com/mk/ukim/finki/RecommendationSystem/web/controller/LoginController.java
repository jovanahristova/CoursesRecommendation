package com.mk.ukim.finki.RecommendationSystem.web.controller;

import com.mk.ukim.finki.RecommendationSystem.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    /*@PostMapping("/processLoginForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("user") User user,
            BindingResult theBindingResult,
            Model model) {

        String username = user.getUsername();
        //logger.info("Processing registration form for: " + username);

        // form validation
        if (theBindingResult.hasErrors()) {
            return "login";
        }

        List<User>

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
*/
    // add request mapping for /access-denied

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";

    }
}
