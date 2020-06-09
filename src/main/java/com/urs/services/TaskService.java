package com.urs.services;

import java.util.List;

import com.urs.models.Task;
import com.urs.models.User;
import com.urs.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Create new task for a user (get user by email)
    public void addTask(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    // Gat all user's tasks
    public List<Task> findUserTask(User user) {
        return taskRepository.findByUser(user);
    }
}