package com.csc510.resa.document;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipes")
public class RecipeDocument {

    @Id
    private String id;
    private String name;
    private String description;
    private String time;
    private String numServings;
    private ArrayList<String> ingredientList;
    private ArrayList<String> instructions;
    private String imageLink;
    private String originalLink;
    private boolean isPrivate;
    private Category category;
    private String creator;

    public RecipeDocument() {}

    /**
     * Constructs Recipe object with all the fields
     *
     * @param name         the name of the Recipe
     * @param ingredients  the ingredients that the recipe needs
     * @param time         the time that the recipe will take to make
     * @param instructions the instructions for making the recipe
     */
    public RecipeDocument(final String name, final String description, final Category category, final String imageLink,
            final String time, final String numServings, final ArrayList<String> ingredients,
            final ArrayList<String> instructions, final String originalLink) {
        this.name = name;
        this.ingredientList = ingredients;
        this.instructions = instructions;
        this.time = time;
        this.numServings = numServings;
        this.description = description;
        this.isPrivate = false;
        this.category = category;
        this.imageLink = imageLink;
        this.originalLink = originalLink;
    }

    public RecipeDocument(final String name, final String description, final Category category, final String imageLink,
            final String time, final String numServings, final ArrayList<String> ingredients,
            final ArrayList<String> instructions, final boolean isPrivate, final String originalLink) {
        this.name = name;
        this.ingredientList = ingredients;
        this.instructions = instructions;
        this.time = time;
        this.numServings = numServings;
        this.description = description;
        this.isPrivate = isPrivate;
        this.category = category;
        this.imageLink = imageLink;
        this.originalLink = originalLink;
    }

    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(final String creator) {
        this.creator = creator;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the ingredientList
     */
    public ArrayList<String> getIngredientList() {
        return ingredientList;
    }

    /**
     * @param ingredientList the ingredientList to set
     */
    public void setIngredientList(final ArrayList<String> ingredientList) {
        this.ingredientList = ingredientList;
    }

    /**
     * @return the instructions
     */
    public ArrayList<String> getInstructions() {
        return instructions;
    }

    /**
     * @param instructions the instructions to set
     */
    public void setInstructions(final ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    /**
     * @return the isPrivate
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * @param isPrivate the isPrivate to set
     */
    public void setPrivate(final boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(final String time) {
        this.time = time;
    }

    /**
     * @return the numServings
     */
    public String getNumServings() {
        return numServings;
    }

    /**
     * @param numServings the numServings to set
     */
    public void setNumServings(final String numServings) {
        this.numServings = numServings;
    }

    /**
     * @return the imageLink
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     * @param imageLink the imageLink to set
     */
    public void setImageLink(final String imageLink) {
        this.imageLink = imageLink;
    }

    /**
     * @return the originalLink
     */
    public String getOriginalLink() {
        return originalLink;
    }

    /**
     * @param originalLink the originalLink to set
     */
    public void setOriginalLink(final String originalLink) {
        this.originalLink = originalLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public enum Category {
        BREAKFAST, LUNCH, DINNER, BEVERAGES, APPETIZERS, DESSERTS, SOUPS, SALADS, OTHER
    }
}

