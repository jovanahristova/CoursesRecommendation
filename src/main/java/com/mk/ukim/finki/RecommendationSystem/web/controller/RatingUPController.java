package com.mk.ukim.finki.RecommendationSystem.web.controller;

import com.mk.ukim.finki.RecommendationSystem.model.*;
import com.mk.ukim.finki.RecommendationSystem.service.ProfessorService;
import com.mk.ukim.finki.RecommendationSystem.service.RatingUPService;
import com.mk.ukim.finki.RecommendationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ratingsUP")
public class RatingUPController {
    private RatingUPService ratingUPService;
    private UserService userService;
    private ProfessorService professorService;

    @Autowired
    public RatingUPController(RatingUPService ratingUPService, UserService userService, ProfessorService professorService){
        this.ratingUPService = ratingUPService;
        this.userService = userService;
        this.professorService = professorService;
    }

    @GetMapping
    public String ratingsUP(Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = this.userService.findByEmail(username);

        List<RatingUP> ratingsUP = this.ratingUPService.findByUser(user);

        model.addAttribute("ratingsUP", ratingsUP);


        return "ratingsUP";
     }

    @GetMapping("/all")
    public String allRatingsUP(HttpServletRequest request, Model model) {
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 20; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        List<RatingUP> ratingsUP = this.ratingUPService.findAll();
        model.addAttribute("ratingsUP", this.ratingUPService.findAll(page, size));
        return "allRatingsUP";

    }


        @GetMapping("/add-newUP")
        public String addNewUPRating (Model model){
//        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String username = loggedInUser.getName();
            model.addAttribute("username", username);
            List<Professor> professors = this.professorService.findAll();
            model.addAttribute("professors", professors);

            model.addAttribute("ratingUP", new RatingUP());


            return "add-UPrating";
        }

        @PostMapping("/{id}/remove")
        public String removeUPRating ( @PathVariable int id){
            RatingUP ratingUP = ratingUPService.findById(id);
            ratingUPService.deleteById(id);

            return "redirect:/ratingsUP";
        }

        @PostMapping("/fromStudentToProfessor")
        public String saveRatingUP (@ModelAttribute @Valid @RequestBody RatingUP ratingUP, BindingResult
        bindingResult, @RequestParam Integer professor, Model model){

            if (bindingResult.hasErrors()) {
                Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
                String username = loggedInUser.getName();
                model.addAttribute("username", username);
                List<Professor> professors = this.professorService.findAll();
                model.addAttribute("professors", professors);

                return "add-UPrating";
            }
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String username = loggedInUser.getName();
            User user = this.userService.findByEmail(username);

            ratingUP.setUser(user);

            this.ratingUPService.save(ratingUP);
            return "redirect:/ratingsUP";

        }

}
