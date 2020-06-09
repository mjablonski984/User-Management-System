package com.urs.services;

import java.util.ArrayList;
import java.util.List;

import com.urs.models.Role;
import com.urs.models.User;
import com.urs.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// @Service marks a class that performs a service (executing business logic,calling external APIs etc.)
@Service
public class UserService {

    // @Autowired - dependency injection
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        // Hash the password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        // Create role for a user, add it to a roles list
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        // Save user
        userRepository.save(user);
    }

    public void createAdmin(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    // Find user by email
    public User findOne(String email) {
        return userRepository.findById(email).orElse(null);
    }

    // Check if user exists
    public boolean isUserPresent(String email) {
        User u = userRepository.findById(email).orElse(null);

        if (u != null)
            return true;

        return false;
    }

    // Get all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Search users by name (use custom findByNameLike method)
    public List<User> findByName(String name) {
        return userRepository.findByNameLike("%" + name + "%");
    }

}