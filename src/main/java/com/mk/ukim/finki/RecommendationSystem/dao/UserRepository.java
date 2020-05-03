package com.mk.ukim.finki.RecommendationSystem.dao;

import com.mk.ukim.finki.RecommendationSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
