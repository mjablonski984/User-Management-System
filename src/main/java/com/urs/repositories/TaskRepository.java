package com.urs.repositories;

import java.util.List;

import com.urs.models.Task;
import com.urs.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Returns list of tasks assigned to a particular user
    List<Task> findByUser(User user);
}