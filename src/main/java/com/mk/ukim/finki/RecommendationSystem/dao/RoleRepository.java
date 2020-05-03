package com.mk.ukim.finki.RecommendationSystem.dao;

import com.mk.ukim.finki.RecommendationSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
