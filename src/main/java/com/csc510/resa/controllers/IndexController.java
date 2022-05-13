package com.csc510.resa.controllers;

import com.csc510.resa.rest.recipe.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("recipes", recipeService.getRecipesForCurrentUser(null));

        return "index";
    }

    @GetMapping("/")
    public String index(Model modeL) {
        return "login";
    }
}
