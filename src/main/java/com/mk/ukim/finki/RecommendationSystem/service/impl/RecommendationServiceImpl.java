package com.mk.ukim.finki.RecommendationSystem.service.impl;

import com.mk.ukim.finki.RecommendationSystem.model.*;
import com.mk.ukim.finki.RecommendationSystem.repository.*;
import com.mk.ukim.finki.RecommendationSystem.service.RecommendationService;
import com.mk.ukim.finki.RecommendationSystem.util.CosineSimilarityCalculator;
import com.mk.ukim.finki.RecommendationSystem.web.dto.CourseRatingDto;
import com.mk.ukim.finki.RecommendationSystem.web.dto.RecommendedCourseDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final RatingUPRepository ratingUPRepository;
    private final RatingUCRepository ratingUCRepository;
    private final StudyProgramRepository studyProgramRepository;

    public RecommendationServiceImpl(ProfessorRepository professorRepository, CourseRepository courseRepository, UserRepository userRepository, RatingUPRepository ratingUPRepository, RatingUCRepository ratingUCRepository, StudyProgramRepository studyProgramRepository) {
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.ratingUPRepository = ratingUPRepository;
        this.ratingUCRepository = ratingUCRepository;
        this.studyProgramRepository = studyProgramRepository;
    }

    @Override
    public List<RecommendedCourseDto> getRecommendations(User user, char term, int limit) {

        int countCourses = this.ratingUCRepository.findAllByUser(user).size();

        List<User> users = this.userRepository.findAll().stream()
                .filter(u -> u.getRole().getName().toString().equals("ROLE_STUDENT"))
                .filter(u -> !u.equals(user))
                .collect(Collectors.toList());
        List<StudyProgram> allStudyPrograms = studyProgramRepository.findAll();
        List<Course> allCourses = courseRepository.findAllByTerm(term);
        List<Professor> allProfessors = professorRepository.findAll();

        List<Course> usersCourses = ratingUCRepository.findAllByUser(user)
                .stream()
                .map(RatingUC::getCourse)
                .collect(Collectors.toList());

        if (countCourses <= 3) {
            List<CourseRatingDto> courseRatingDtos = ratingUCRepository.groupByCourseId();
            Comparator<CourseRatingDto> dtoComparator = Comparator.comparingDouble(dto -> dto.rating);
            dtoComparator = dtoComparator.reversed();
            return courseRatingDtos.stream().sorted(dtoComparator)
                    .filter(dto -> courseRepository.findById(dto.courseId).get().getTerm() == term)
                    .map(dto -> new RecommendedCourseDto(courseRepository.findById(dto.courseId).get().getName(), dto.rating))
                    .limit(limit)
                    .collect(Collectors.toList());
        } else if (countCourses <= 5) {
            Map<String,Double> currentUserDictionary = createDictionaryForUser(allStudyPrograms, allCourses, allProfessors, user);

            Map<User, Double> similarityMap = new LinkedHashMap<>();
            for (User u : users) {
                Map<String, Double> dict = createDictionaryForUser(allStudyPrograms, allCourses, allProfessors, u);
                double similarity = CosineSimilarityCalculator.cosineSimilarity(currentUserDictionary.values(), dict.values());
                similarityMap.put(u, similarity);
            }

            List<User> mostSimilarUsers = similarityMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(4)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());


            List<Course> coursesFromMostSimilarUsers = mostSimilarUsers.stream()
                    .flatMap(u -> ratingUCRepository.findAllByUser(u).stream()
                            .map(RatingUC::getCourse)
                            .filter(course -> course.getTerm()==term))
                    .filter(course -> !usersCourses.contains(course))
                    .distinct()
                    .limit(limit)
                    .collect(Collectors.toList());


            List<Double> coursesRatings = coursesFromMostSimilarUsers.stream()
                    .map(this::getCourseRating)
                    .collect(Collectors.toList());

            List<RecommendedCourseDto> result = new ArrayList<>();
            for (int i=0;i<coursesFromMostSimilarUsers.size();i++) {
                result.add(new RecommendedCourseDto(coursesFromMostSimilarUsers.get(i).getName(), coursesRatings.get(i)));
            }

            Comparator<RecommendedCourseDto> courseDtoComparator = Comparator.comparingDouble(RecommendedCourseDto::getAverageRating).reversed();
            result.sort(courseDtoComparator);

            return result;
        }
        else {

            List<Course> otherCourses = allCourses.stream()
                    .filter(c -> !usersCourses.contains(c))
                    .collect(Collectors.toList());

            List<Map<String,Double>> otherCoursesDicts = new ArrayList<>();
            for (Course course : otherCourses) {
                otherCoursesDicts.add(createDictionaryForCourse(users, course));
            }

            List<Map<String,Double>> userCoursesDicts = new ArrayList<>();
            for (Course course : usersCourses) {
                userCoursesDicts.add(createDictionaryForCourse(users, course));
            }

            Map<Course, Double> similarityMap = new LinkedHashMap<>();

            for (int i=0;i<otherCourses.size();i++) {
                double average = 0.0;
                for (Map<String,Double> userCourseDict : userCoursesDicts) {
                    average+=CosineSimilarityCalculator.cosineSimilarity(
                            userCourseDict.values(),
                            otherCoursesDicts.get(i).values()
                    );
                }
                average/=userCoursesDicts.size();
                similarityMap.put(otherCourses.get(i), average);
            }



            return similarityMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(entry -> new RecommendedCourseDto(entry.getKey().getName(), getCourseRating(entry.getKey()), entry.getValue()))
                    .limit(limit)
                    .collect(Collectors.toList());
        }
    }

    private double getCourseRating(Course course) {
        return ratingUCRepository.findAllByCourse(course)
                .stream()
                .mapToInt(RatingUC::getRating)
                .average()
                .orElse(0);
    }

    private Map<String, Double> createDictionaryForCourse(List<User> users, Course course) {
        Map<String,Double> dict = new LinkedHashMap<>();

        for (User u : users) {
            Optional<RatingUC> ratingUC = ratingUCRepository.findByUserAndCourse(u, course);
            double rating = ratingUC.map(RatingUC::getRating).orElse(0);
            dict.put("Student"+u.getId(), rating);
        }

        return dict;
    }



    private Map<String, Double> createDictionaryForUser(List<StudyProgram> allStudyPrograms, List<Course> allCourses, List<Professor> allProfessors, User u) {
        Map<String, Double> dict = new LinkedHashMap<>();

        String[] years = {"1", "2", "3", "4"};
        for (String year : years) {
            dict.put("godina" + year, year.equals(u.getYearStudies()) ? 1.0 : 0.0);
        }

        for (StudyProgram sp : allStudyPrograms) {
            dict.put("Programa" + sp.getCode(), u.getStudyProgram()==sp ? 1.0 : 0.0);
        }

        for (Course course : allCourses) {
            Optional<RatingUC> ratingUC = ratingUCRepository.findByUserAndCourse(u, course);
            Integer rating = ratingUC.map(RatingUC::getRating).orElse(0);
            dict.put("kurs" + String.valueOf(course.getId()), (double) rating);
        }

        for (Professor professor : allProfessors) {
            Double rating = ratingUPRepository.findAllByUserAndProfessor(u, professor)
                    .stream()
                    .mapToDouble(RatingUP::getRating)
                    .average()
                    .orElse(0);
            dict.put("profesor" + String.valueOf(professor.getId()), rating);
        }
        return dict;
    }


}
