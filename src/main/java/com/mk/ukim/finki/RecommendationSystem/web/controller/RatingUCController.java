package com.mk.ukim.finki.RecommendationSystem.web.controller;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import com.mk.ukim.finki.RecommendationSystem.model.RatingUC;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.service.*;
import com.mk.ukim.finki.RecommendationSystem.web.dto.RecommendedCourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ratingsUC")
public class RatingUCController {
    private RatingUCService ratingUCService;
    private CourseService courseService;
    private UserService userService;
    private RecommendationService recommendationService;
    private ProfessorService professorService;

    @Autowired
    public RatingUCController(RatingUCService ratingUCService, CourseService courseService, UserService userService, RecommendationService recommendationService, ProfessorService professorService){
        this.ratingUCService = ratingUCService;
        this.courseService = courseService;
        this.userService = userService;
        this.recommendationService = recommendationService;
        this.professorService = professorService;
    }

    @PostMapping("/recommendation")
    public String proba(Model model,  @RequestParam Integer limit, @RequestParam Character term){

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = this.userService.findByEmail(username);
        List<RecommendedCourseDto> result  = recommendationService.getRecommendations(user, term, limit);
       // List<Professor> professors = new ArrayList<>();
        for(RecommendedCourseDto r : result){
            Course course = this.courseService.findCourseByName(r.courseName);
          //  professors = course.getProfessors();
            r.setProfessorList(course.getProfessors());
            r.setAssistantList(course.getAssistants());
         }
        model.addAttribute("result", result);
        return "recommendedcourses";
    }

    @GetMapping
    public String ratingsUC(HttpServletRequest request, Model model){
        // ova e za konkreten user
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 10; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = this.userService.findByEmail(username);

        List<RatingUC> ratingsUC = this.ratingUCService.findByUser(user);

        //jovance uspori malce ne e okej
        model.addAttribute("ratingsUC", ratingsUC);


        return "ratingsUC";
    }

    @GetMapping("/all")
    public String allRatingsUC(HttpServletRequest request, Model model){
        // ova e za admin
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 20; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
       // List<RatingUC> ratingsUC = this.ratingUCService.findAll();

        model.addAttribute("ratingsUC", this.ratingUCService.findAll(page,size));

        return "allRatingsUC";
    }

    @GetMapping("/add-newUC")
    public String addNewUCRating(Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        model.addAttribute("username", username);
        List<Course> courses = this.courseService.findAll();
        model.addAttribute("courses", courses);

        model.addAttribute("ratingUC", new RatingUC());


        return "add-UCrating";
    }

    @PostMapping("/fromStudentToCourse")
    public String saveRatingUC(@ModelAttribute @Valid @RequestBody RatingUC ratingUC, BindingResult bindingResult, @RequestParam Integer course, Model model){

        if(bindingResult.hasErrors()){
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String username = loggedInUser.getName();
            model.addAttribute("username", username);
            List<Course> courses = this.courseService.findAll();
            model.addAttribute("courses", courses);

            return "add-UCrating";
        }
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = this.userService.findByEmail(username);

        ratingUC.setUser(user);

        this.ratingUCService.save(ratingUC);
        return  "redirect:/ratingsUC";

    }

    @PostMapping("/{id}/remove")
    public String removeUCRating(@PathVariable int id){
        RatingUC ratingUC = ratingUCService.findById(id);
        ratingUCService.deleteById(id);

        return  "redirect:/ratingsUC";
    }
}
