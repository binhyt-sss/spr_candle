package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final CustomerService customerService;

    public ManagerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("")
    public String dashboard() {
        return "manager/dashboard";
    }

} 