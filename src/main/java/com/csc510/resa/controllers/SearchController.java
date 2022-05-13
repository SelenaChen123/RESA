package com.csc510.resa.controllers;

import java.util.Map;

import com.csc510.resa.rest.recipe.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    
    @Autowired
    RecipeService recipeService;

    @GetMapping("search")
    public String search(Model model,  @RequestParam Map<String, String> searchAndFilter) {
        if (searchAndFilter.size() != 0) {
            model.addAttribute("recipes", recipeService.getRecipesForCurrentUser(searchAndFilter));
        }

        return "search";
    }
}
