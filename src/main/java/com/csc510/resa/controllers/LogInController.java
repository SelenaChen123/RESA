package com.csc510.resa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {
    
    @GetMapping("login")
    public String login(Model model) {
        return "login";
    }
}
