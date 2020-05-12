package com.mk.ukim.finki.RecommendationSystem.repository;

import com.mk.ukim.finki.RecommendationSystem.model.Course;
import com.mk.ukim.finki.RecommendationSystem.model.RatingUC;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.web.dto.CourseRatingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingUCRepository extends JpaRepository<RatingUC, Integer> {

    List<RatingUC> findAllByUser(User user);

   @Query("SELECT new com.mk.ukim.finki.RecommendationSystem" +
           ".web.dto.CourseRatingDto(r.course, AVG (r.rating))" +
           "from RatingUC as r " +
           "group by r.course")
    List<CourseRatingDto> groupByCourseId ();

   List<RatingUC> findAllByCourse(Course course);



   Optional<RatingUC> findByUserAndCourse (User user, Course course);

}
