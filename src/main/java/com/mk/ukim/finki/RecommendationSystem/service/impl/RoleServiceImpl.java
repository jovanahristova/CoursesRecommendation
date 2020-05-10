package com.mk.ukim.finki.RecommendationSystem.service.impl;

import com.mk.ukim.finki.RecommendationSystem.model.Role;
import com.mk.ukim.finki.RecommendationSystem.repository.RoleRepository;
import com.mk.ukim.finki.RecommendationSystem.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(int id) {
        return this.roleRepository.findById(id).orElse(null);
    }
}
