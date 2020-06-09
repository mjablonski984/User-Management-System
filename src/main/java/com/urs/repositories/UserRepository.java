package com.urs.repositories;

import java.util.List;

import com.urs.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<object , type of primary key>
public interface UserRepository extends JpaRepository<User, String> {
    // Method used to filter users by name
    List<User> findByNameLike(String name);
}