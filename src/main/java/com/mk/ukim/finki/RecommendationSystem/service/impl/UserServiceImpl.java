package com.mk.ukim.finki.RecommendationSystem.service.impl;

import com.mk.ukim.finki.RecommendationSystem.model.ERole;
import com.mk.ukim.finki.RecommendationSystem.model.exceptions.*;
import com.mk.ukim.finki.RecommendationSystem.repository.RoleRepository;
import com.mk.ukim.finki.RecommendationSystem.repository.StudyProgramRepository;
import com.mk.ukim.finki.RecommendationSystem.repository.UserRepository;

import com.mk.ukim.finki.RecommendationSystem.model.Role;
import com.mk.ukim.finki.RecommendationSystem.model.StudyProgram;
import com.mk.ukim.finki.RecommendationSystem.model.User;
import com.mk.ukim.finki.RecommendationSystem.service.UserService;
import com.mk.ukim.finki.RecommendationSystem.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private  final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudyProgramRepository studyProgramRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, StudyProgramRepository studyProgramRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.studyProgramRepository = studyProgramRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRole(new Role(ERole.ROLE_STUDENT));
        user.setStudyProgram(registration.getStudyProgram());
        user.setYearStudies(registration.getYearStudies());
        return userRepository.save(user);
    }



    @Override
    public User createUser(String firstName, String lastName, String yearStudies, String email,String password, int studyProgramId) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setYearStudies(yearStudies);
        user.setEmail(email);
//        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new RoleIdNotFoundException(roleId));
//        user.setRole(role);
        user.setPassword(password);
        StudyProgram studyProgram = this.studyProgramRepository.findById(studyProgramId).orElseThrow(() -> new StudyProgramNotFoundException(studyProgramId));
        user.setStudyProgram(studyProgram);
//        if(email.equals(userRepository.findByEmail(email))){
//            throw new InvalidUserEmail();
//        } else{
//            return this.userRepository.save(user);
//        }

        return this.userRepository.save(user);
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }


    @Override
    public User editUser(int id, String firstName, String lastName, String yearStudies, String email, String password, int studyProgramId) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setYearStudies(yearStudies);
        user.setEmail(email);
//        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new RoleIdNotFoundException(roleId));
//        user.setRole(role);
        user.setPassword(password);
        StudyProgram studyProgram = this.studyProgramRepository.findById(studyProgramId).orElseThrow(() -> new StudyProgramNotFoundException(studyProgramId));
        user.setStudyProgram(studyProgram);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User edit(int id, User user) {
        User u = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setYearStudies(user.getYearStudies());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        StudyProgram studyProgram = this.studyProgramRepository.findById(user.getStudyProgram().getId()).orElseThrow(() -> new StudyProgramNotFoundException(user.getStudyProgram().getId()));
        user.setStudyProgram(studyProgram);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(int id) {
       this.userRepository.deleteById(id);
    }
}
