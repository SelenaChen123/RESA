package com.csc510.resa.rest.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping(value = "/extractRecipe")
    public void extractRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getParameter("link");
        recipeService.extractRecipe(url);
        response.sendRedirect("/home");
    }

    @PostMapping(value = "/createRecipe")
    public void createRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String instructions = request.getParameter("instructions");
        String ingredients = request.getParameter("ingredients");
        String privacy = request.getParameter("privacy");
        String originalLink = "";
        String description = request.getParameter("description");
        String time = request.getParameter("time");
        String servings = request.getParameter("servings");
        String imageLink = request.getParameter("image-url");

        ArrayList<String> instructionsList = parseInstructions(instructions);
        ArrayList<String> ingredientsList = parseIngredients(ingredients);

        boolean isPrivate = false;
        if (privacy.equals("private")) {
            isPrivate = true;
        }

        recipeService.createRecipe(title, description, category, imageLink, time, servings, ingredientsList, instructionsList, isPrivate, originalLink);
        response.sendRedirect("/home");
    }

    @PostMapping(value = "/cancelRecipe")
    public void cancelRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/home");
    }

    public ArrayList<String> parseInstructions(String instructions) {
        Scanner instructionScanner = new Scanner(instructions);
        instructionScanner.useDelimiter("\n");
        ArrayList<String> instructionsList = new ArrayList<String>();

        while(instructionScanner.hasNext()) {
            String instruction = instructionScanner.next();
            instructionsList.add(instruction);
        }
        instructionScanner.close();
        return instructionsList;
    }

    public ArrayList<String> parseIngredients(String ingredients) {
        Scanner ingreidentScanner = new Scanner(ingredients);
        ingreidentScanner.useDelimiter("\n");
        ArrayList<String> ingredientsList = new ArrayList<String>();

        while(ingreidentScanner.hasNext()) {
            String ingredient = ingreidentScanner.next();
            ingredientsList.add(ingredient);
        }
        ingreidentScanner.close();
        return ingredientsList;
    }
}
