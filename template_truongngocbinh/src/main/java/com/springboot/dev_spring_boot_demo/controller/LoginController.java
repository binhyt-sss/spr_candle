package com.springboot.dev_spring_boot_demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout") // Thay đổi từ @PostMapping
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/system")
    public String system(){
        return "system";
    }
    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
