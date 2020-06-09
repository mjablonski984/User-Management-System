package com.urs.controllers;

import com.urs.services.UserService;

import javax.validation.Valid;

import com.urs.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        // Add user attribute and render register form
        model.addAttribute("user", new User());
        return "views/registerForm";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        // If user data didn't passed validation(@Valid) display errors
        if (bindingResult.hasErrors()) {
            return "views/registerForm";
        }
        // if user exists add attr(exist) to show alert in a form
        if (userService.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "views/registerForm";
        }
        // Create user if passed validation
        userService.createUser(user);

        return "views/success";
    }

}