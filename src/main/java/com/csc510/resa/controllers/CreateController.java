package com.csc510.resa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateController {
    
    @GetMapping("create")
    public String create(Model model) {
        return "create";
    }
}
