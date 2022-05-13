package com.csc510.resa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class UserAccountController {
    
    @GetMapping("account")
    public String account(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<String> links = new ArrayList<>();
        links.add("Recipe Link 1");
        links.add("Recipe Link 2");
        links.add("Recipe Link 3");
        links.add("Recipe Link 4");

        model.addAttribute("username", username);
        model.addAttribute("links", links);

        return "account";
    }
}
