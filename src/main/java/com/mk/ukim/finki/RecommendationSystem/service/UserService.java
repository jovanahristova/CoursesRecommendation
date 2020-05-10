package com.mk.ukim.finki.RecommendationSystem.service;

import com.mk.ukim.finki.RecommendationSystem.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

   // Page<User> findAll(int page, int size);

    User findById(int id);

    User findUserByEmail(String email);

   // Page<User> searchUser(String email,int page,int size);

    User createUser(String firstName, String lastName, String yearStudies, String email, String password, int studyProgramId);

    User create(User user);

    User editUser(int id, String firstName, String lastName, String yearStudies, String email, String password, int studyProgramId);

    User findByUsername(String username);
    User edit(int id, User user);
    void deleteUserById(int id);
}
