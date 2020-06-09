package com.urs.controllers;

import java.security.Principal;

import com.urs.models.User;
import com.urs.services.TaskService;
import com.urs.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    // Functionallity is available only for logged in user -
    // allows to use Principal to access user id(in this case email)
    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal) {

        String email = principal.getName();
        User user = userService.findOne(email);
        // Pass list of tasks belonging to a user to a view
        model.addAttribute("tasks", taskService.findUserTask(user));

        return "views/profile";
    }
}