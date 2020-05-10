/*
package com.mk.ukim.finki.RecommendationSystem.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.mk.ukim.finki.RecommendationSystem.model.ERole;
import com.mk.ukim.finki.RecommendationSystem.model.Role;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.payload.request.LoginRequest;
import com.mk.ukim.finki.RecommendationSystem.payload.request.SignupRequest;
import com.mk.ukim.finki.RecommendationSystem.payload.response.JwtResponse;
import com.mk.ukim.finki.RecommendationSystem.payload.response.MessageResponse;
import com.mk.ukim.finki.RecommendationSystem.repository.RoleRepository;
import com.mk.ukim.finki.RecommendationSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/auth")
public class AuthController {
//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

//    @Autowired
//    JwtUtils jwtUtils;

    @GetMapping("/signin")
    public String userSignIn(Model model){
        return "sign-in";
    }

//    @PostMapping("/users")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles));
//    }

    @GetMapping("/registerNewUser")
    public String userSignUp(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

//    @Valid Course course,
//    BindingResult bindingResult,
//    Model model){
//        if (bindingResult.hasErrors()) {
//            List<Professor> professors = this.professorService.findAll();
//            model.addAttribute("professors", professors);
//            List<Professor> assistants = this.professorService.findAll();
//            model.addAttribute("assistants", assistants);
//            return "add-course";
//        }
//        this.courseService.createCourse(course);
//        return "redirect:/courses";

    @PostMapping("/users")
    public  String prova(@Valid User user, Model model){

        this.userRepository.save(user);
        return  "redirect:/home";
    }


    @PostMapping("/registeredUsers")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest, @Valid User u) {
        Optional<User> use1 = this.userRepository.findById(u.getId());
        String jovana = "jovana";
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}*/
