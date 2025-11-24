package com.hospitalManagement.Proj1.controller;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.hospitalManagement.Proj1.model.Users;
import com.hospitalManagement.Proj1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String first()
    {

        return "welcome";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/logout")
    public String logout()
    {
        return "logout";
    }

    @GetMapping("/register")
    public String addUser(Model model)
    {
        model.addAttribute("users",new Users());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute Users users)
    {
        userService.addUser(users);
//        return "redirect:/user/home";
        return "dashboard";
    }

    @GetMapping("/session-expired")
    public String sessionExpired() {
        return "session-expired";
    }

    @GetMapping("/userHomePage")
    public String userHomePage()
    {
        return "userHomePage";
    }



}
