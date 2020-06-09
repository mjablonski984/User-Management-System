package com.urs.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.urs.services.TaskService;
import com.urs.services.UserService;
import com.urs.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping("/addTask")
    public String taskForm(String email, Model model, HttpSession session) {
        // Pass email to a session
        session.setAttribute("email", email);
        // Pass new task object to a view
        model.addAttribute("task", new Task());
        return "views/taskForm";
    }

    @PostMapping("/addTask")
    public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "views/taskForm";
        }
        // Get the email from the session to assign task to correct user
        String email = (String) session.getAttribute("email");
        taskService.addTask(task, userService.findOne(email));

        return "redirect:/users";
    }
}