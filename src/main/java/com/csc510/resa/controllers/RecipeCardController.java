package com.csc510.resa.controllers;

import java.util.ArrayList;

import com.csc510.resa.model.Recipe;

import com.csc510.resa.rest.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeCardController {

    @Autowired
    RecipeService recipeService;
    
    @GetMapping("recipe")
    public String recipeCard(Model model, @RequestParam String id) {
        Recipe r = recipeService.getRecipeById(id);

        if(r == null) {
            return "index";
        }
        ArrayList<String> ingredients = r.ingredientList;
        ArrayList<String> filteredList = new ArrayList<String>();
        for(int i=0;i<ingredients.size();i++){
            String ingredient = ingredients.get(i);
            ingredient = ingredient.replaceAll("[^A-Za-z ]","");
            ingredient = ingredient.trim();
            filteredList.add(ingredient);
            
        }
        model.addAttribute("filtered",filteredList);
        model.addAttribute("recipe", r);
        return "recipe-card";
    }
}
