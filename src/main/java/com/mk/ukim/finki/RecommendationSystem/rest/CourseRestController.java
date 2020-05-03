package com.mk.ukim.finki.RecommendationSystem.rest;

import com.mk.ukim.finki.RecommendationSystem.entity.Course;
import com.mk.ukim.finki.RecommendationSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseRestController {
    private CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable int courseId) {

        Course theCourse = courseService.findById(courseId);

        if (theCourse == null) {
            throw new RuntimeException("Course id not found - " + courseId);
        }

        return theCourse;
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course theCourse) {

        theCourse.setId(0);

        courseService.save(theCourse);

        return theCourse;
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course theCourse) {

        courseService.save(theCourse);

        return theCourse;
    }

    @DeleteMapping("/courses/{courseId}")
    public String deleteCourse(@PathVariable int courseId) {

        Course tempCourse = courseService.findById(courseId);

        if (tempCourse == null) {
            throw new RuntimeException("Course id not found - " + courseId);
        }

        courseService.deleteById(courseId);

        return "Deleted course id - " + courseId;
    }
}
