package com.mk.ukim.finki.RecommendationSystem.web.controller;


import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

   /* @GetMapping
    public String getCoursePage(Model model){
        List<Course> courses = this.courseService.findAll();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/add-new")
    public String addNewCoursePage(Model model){
        model.addAttribute("course", new Course());
        return  "add-course";
    }

    @GetMapping("/{id}/edit")
    public String editCoursePage(@PathVariable int id, Model model){
        try {
            Course course = this.courseService.findById(id);
            model.addAttribute("course", course);
            return "add-course";

        }catch (RuntimeException ex){
            return "redirect:/courses?error=" + ex.getLocalizedMessage();
        }
    }

    @PostMapping
    public String saveCourse(@Valid Course course,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()) {
            return "add-course";
        }
        this.courseService.createCourse(course);
        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable int id){
        this.courseService.deleteCourseById(id);

        return  "redirect:/courses";
    }
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }*/

    @GetMapping
    public String getStudentsPage(Model model){
        List<User> users = this.userService.findAll();
        model.addAttribute("users", users);
        return "students";
    }

  


    @PostMapping
    public User createUser(@RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "lastName", required = false) String lastName,
                           @RequestParam(value = "yearStudies", required = false) String yearStudies,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "password", required = false) String password,
                           @RequestParam(value = "studyProgramId", required = false) int studyProgramId) {
        //String firstName, String lastName, String yearStudies, String email, int roleId, String password, int studyProgramId
        User user = userService.createUser(firstName, lastName, yearStudies, email, password, studyProgramId);
        return user;
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable int userId,
                           @RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "lastName", required = false) String lastName,
                           @RequestParam(value = "yearStudies", required = false) String yearStudies,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "password", required = false) String password,
                           @RequestParam(value = "studyProgramId", required = false) int studyProgramId) {

        User user = userService.editUser(userId, firstName, lastName, yearStudies, email, password, studyProgramId);
        return user;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUserById(userId);
    }
}
