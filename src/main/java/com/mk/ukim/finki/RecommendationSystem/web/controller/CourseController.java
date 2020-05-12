package com.mk.ukim.finki.RecommendationSystem.web.controller;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import com.mk.ukim.finki.RecommendationSystem.model.Professor;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.ProfessorAlreadyExistsException;
import com.mk.ukim.finki.RecommendationSystem.service.CourseService;
import com.mk.ukim.finki.RecommendationSystem.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/courses")
public class CourseController {
    private CourseService courseService;
    private ProfessorService professorService;

    @Autowired
    public CourseController(CourseService courseService, ProfessorService professorService){
        this.courseService = courseService;
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public String coursesPage(HttpServletRequest request, Model model) {

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 10; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("courses", this.courseService.findAll(page, size));

        return "courses-all";
    }


    @GetMapping
    public String getCoursePage(HttpServletRequest request, Model model){

        List<Course> courses = this.courseService.findAll();
        model.addAttribute("courses", courses);
        return "redirect:/courses/all";
    }

    @GetMapping("/add-new")
    public String addNewCoursePage(Model model){
        List<Professor> professors = this.professorService.findAll();
        List<Professor> assistants = this.professorService.findAll();
        model.addAttribute("professors", professors);
        model.addAttribute("assistants", assistants);
        model.addAttribute("course", new Course());
        return  "add-course";
    }
    @GetMapping("/{id}/PAtoCourse")
    public String addPAtoCourse(@PathVariable int id, Model model){
        Course course = this.courseService.findById(id);
        List<Professor> professors = this.professorService.findAll();
        List<Professor> assistants = this.professorService.findAll();

        model.addAttribute("course", course);
        model.addAttribute("professors", professors);
        model.addAttribute("assistants", assistants);

        return "add-PA-course";
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
    @GetMapping("/{id}/professorsToCourse")
    public String professorsToCourse(@PathVariable int id, Model model){
        Course course = this.courseService.findById(id);
        model.addAttribute("course", course);

        List<Professor> professors = course.getProfessors();
        model.addAttribute("professors", professors);


        return "professorsToCourse";
    }



    @GetMapping("/{id}/assistantsToCourse")
    public String assistantsToCourse(@PathVariable int id, Model model){
      /*  int page = 0; //default page number is 0 (yes it is weird)
        int size = 10; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }*/
        Course course = this.courseService.findById(id);
        model.addAttribute("course", course);

        List<Professor> assistants = course.getAssistants();
        model.addAttribute("assistants", assistants);


        return "assistantsToCourse";
    }

    @PostMapping("/PAtoCourse")
    public String savePAtoCourse(@ModelAttribute @Valid @RequestBody Course course, BindingResult bindingResult, @RequestParam Integer professors ,
                                 @RequestParam Integer assistants, Model model)  {
        if (bindingResult.hasErrors()) {
            List<Professor> p = this.professorService.findAll();
            model.addAttribute("professors", p);
            List<Professor> a = this.professorService.findAll();
            model.addAttribute("assistants", a);
            return "add-PA-course";
        }
        Course c = this.courseService.findById(course.getId());
        Professor professor = this.professorService.findById(professors);
        Professor assistant = this.professorService.findById(assistants);

//        c.getProfessors().add(professor);
//        c.getAssistants().add(assistant);

        model.addAttribute("professor", professor);
        model.addAttribute("assistant", assistant);
        //this.courseService.saveProfessorAssistantToCourse(c, professors, assistants);
        try {
            this.courseService.saveProfessorAssistantToCourse(c, professors, assistants);
        } catch (ProfessorAlreadyExistsException e) {
            e.printStackTrace();
        }
        // this.courseService.createCourse(c);

        return "redirect:/courses";

    }

    @PostMapping
    public String saveCourse(@Valid Course course,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()) {
            List<Professor> professors = this.professorService.findAll();
            model.addAttribute("professors", professors);
            List<Professor> assistants = this.professorService.findAll();
            model.addAttribute("assistants", assistants);
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

    @PostMapping("/{id}/removeProfessor/{idP}")
    public String removeProfesorFromCourse(@PathVariable int id, @PathVariable int idP){
        Course course = this.courseService.findById(id);
        Professor professor = this.professorService.findById(idP);

        course.getProfessors().remove(professor);
        this.courseService.editCourse(course.getId(), course);

        return  "redirect:/courses";
    }

    @PostMapping("/{id}/removeAssistant/{idA}")
    public String removeAssistantFromCourse(@PathVariable int id, @PathVariable int idA){
        Course course = this.courseService.findById(id);
        Professor assistant = this.professorService.findById(idA);

        course.getAssistants().remove(assistant);
        this.courseService.editCourse(course.getId(), course);

        return  "redirect:/courses";
    }



}
