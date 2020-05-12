package com.mk.ukim.finki.RecommendationSystem.web.dto;

import com.mk.ukim.finki.RecommendationSystem.model.Professor;

import java.util.List;

public class RecommendedCourseDto {

    public String courseName;
    public double averageRating;
    public double match;
    public List<Professor> professorList;

    public List<Professor> assistantList;

    public RecommendedCourseDto(String courseName, double averageRating, double match) {
        this.courseName = courseName;
        this.averageRating = averageRating;
        this.match = match*100.0;
    }

    public RecommendedCourseDto(String courseName, double averageRating) {
        this.courseName = courseName;
        this.averageRating = averageRating;
        this.match = 100.0;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getMatch() {
        return match;
    }

    public String getMatchToString() {
        return String.format("%.2f", match);
    }

    public String getRatingToString(){
        return String.format("%.2f", averageRating);
    }

    public void setMatch(double match) {
        this.match = match;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<Professor> getAssistantList() {
        return assistantList;
    }

    public void setAssistantList(List<Professor> assistantList) {
        this.assistantList = assistantList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }
}
