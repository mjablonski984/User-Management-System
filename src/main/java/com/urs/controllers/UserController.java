package com.urs.controllers;

import com.urs.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Default value of search is set to "" - shows all users
    @GetMapping("/users")
    public String listUsers(Model model, @RequestParam(defaultValue = "") String name) {
        // pass data object to a view (name, data - here list of users )
        model.addAttribute("users", userService.findByName(name));
        return "views/list";
    }

}