package com.csc510.resa.model;

import com.csc510.resa.document.RecipeDocument;

import java.util.ArrayList;

public class Recipe {

    public String id;
    public String creator;
    public String name;
    public String description;
    public String time;
    public String numServings;
    public ArrayList<String> ingredientList;
    public ArrayList<String> instructions;
    public String imageLink;
    public String originalLink;
    public boolean isPrivate;
    public Category category;

    public enum Category {
        BREAKFAST, LUNCH, DINNER, BEVERAGES, APPETIZERS, DESSERTS, SOUPS, SALADS, OTHER
    }

    public Recipe() {}

    public Recipe(RecipeDocument doc) {
        this.id = doc.getId();
        this.creator = doc.getCreator();
        this.name = doc.getName();
        this.description = doc.getDescription();
        this.time = doc.getTime();
        this.numServings = doc.getNumServings();
        this.ingredientList = doc.getIngredientList();
        this.instructions = doc.getInstructions();
        this.imageLink = doc.getImageLink();
        this.originalLink = doc.getOriginalLink();
        this.isPrivate = doc.isPrivate();
        this.category = convertCategory(doc);
    }

    private Category convertCategory(RecipeDocument doc) {
        switch(doc.getCategory()) {
            case LUNCH:
                return Category.LUNCH;
            case SOUPS:
                return Category.SOUPS;
            case DINNER:
                return Category.DINNER;
            case SALADS:
                return Category.SALADS;
            case DESSERTS:
                return Category.DESSERTS;
            case BEVERAGES:
                return Category.BEVERAGES;
            case BREAKFAST:
                return Category.BREAKFAST;
            case APPETIZERS:
                return Category.APPETIZERS;
            case OTHER:
            default:
                return Category.OTHER;
        }
    }
}
