package com.hospitalManagement.Proj1.controller;

import com.hospitalManagement.Proj1.model.LoginVO;
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
    public String login(Model model)
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
        return "redirect:/login";
    }
//    @PostMapping("/login")
//    public String loginToDashboard(Authentication Authentication, @ModelAttribute LoginVO loginVO){
//
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String role = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .findFirst()
//                .orElse("NO_ROLE");
//        System.out.println("Role of user is -----> "+role);
//
//        if(role.equalsIgnoreCase("ROLE_USER"))
//            return "patient-dashboard";
//        if(role.equalsIgnoreCase("ROLE_DOCTOR"))
//            return "doctor-dashboard";
//        if(role.equalsIgnoreCase("ROLE_ADMIN"))
//            return "admin-dashboard";
//        else
//            return "blank-page";
//
//    }

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
