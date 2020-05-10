package com.mk.ukim.finki.RecommendationSystem.repository;

import com.mk.ukim.finki.RecommendationSystem.model.ERole;
import com.mk.ukim.finki.RecommendationSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName (ERole name);
}
