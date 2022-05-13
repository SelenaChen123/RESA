package com.csc510.resa.extract;

import java.io.IOException;
import java.util.ArrayList;

import com.csc510.resa.document.RecipeDocument;
import com.csc510.resa.document.RecipeDocument.Category;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Extracts recipe information
 * 
 * @author Selena Chen
 */
public class RecipeExtractor {

    public static void main(String[] args) {
        // // Sets testing constants
        // String allRecipesChocolateChipCookies = "https://www.allrecipes.com/recipe/10813/best-chocolate-chip-cookies/";
        // String allRecipesBBQCorn = "https://www.allrecipes.com/recipe/14506/";

        // String foodNetWorkChocolateChipCookies = "https://www.foodnetwork.com/recipes/food-network-kitchen/chocolate-chip-cookies-recipe4-2011856";
        // String foodNetworkBlackEyedPeaSoup = "https://www.foodnetwork.com/recipes/food-network-kitchen/black-eyed-pea-soup-3361891";

        // // Extracts recipe
        // String URL = allRecipesChocolateChipCookies;
        // RecipeDocument recipe = extract(URL);

        // // Prints recipe information
        // System.out.println();
        // System.out.println("URL: " + recipe.getoriginalLink() + "\n");
        // System.out.println("Image URL: " + recipe.getImageLink() + "\n");
        // System.out.println("Name: " + recipe.getName() + "\n");
        // System.out.println("Category: " + recipe.getCategory().toString() + "\n");
        // System.out.println("Description: " + recipe.getDescription() + "\n");
        // System.out.println("Time: " + recipe.getTime() + "\n");
        // System.out.println("Servings: " + recipe.getNumServings() + "\n");
        // System.out.println("Ingredients: " + recipe.getIngredientList().toString() +
        // "\n");
        // System.out.println("Directions: " + recipe.getInstructions().toString() +
        // "\n");
        // System.out.println();
    }

    public static RecipeDocument extract(String URL) {
        RecipeDocument recipe = null;

        if (URL.contains("allrecipes.com")) {
            recipe = extractAllrecipes(URL);
        } else if (URL.contains("foodnetwork.com")) {
            recipe = extractFoodnetwork(URL);
        }

        return recipe;
    }

    public static RecipeDocument extractAllrecipes(String URL) {
        // Gets the page
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return null;
        }

        // Gets full URL
        String fullURL = doc.select("button.ugc-upload-button").first().attr("data-login-url");
        fullURL = fullURL.substring(fullURL.lastIndexOf("url=") + 4);

        // Gets the category
        Elements paths = doc.select("a.breadcrumbs__link");
        Category category = null;
        String categoryString = "";
        try{
            categoryString = paths.get(2).text();
        }
        catch(Exception e){
            categoryString = "Uncategorized";
        }
        
        if (categoryString.contains("Breakfast")) {
            category = Category.BREAKFAST;
        } else if (categoryString.contains("Lunch")) {
            category = Category.LUNCH;
        } else if (categoryString.contains("Dinner")) {
            category = Category.DINNER;
        } else if (categoryString.contains("Drink")) {
            category = Category.BEVERAGES;
        } else if (categoryString.contains("Appetizer")) {
            category = Category.APPETIZERS;
        } else if (categoryString.contains("Dessert")) {
            category = Category.DESSERTS;
        } else if (categoryString.contains("Soup")) {
            category = Category.SOUPS;
        } else if (categoryString.contains("Salad")) {
            category = Category.SALADS;
        } else {
            category = Category.OTHER;
        }

        // Gets the page in print view
        String printViewURL = "?printview";
        URL = fullURL + printViewURL;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            System.exit(0);
        }

        // Gets an image
        Element image = doc.select("div.printedRecipe__image").first();
        Element imgs = image.select("img").first();
        String img = imgs.absUrl("src");

        // Gets the name
        String name = doc.select("div.printedRecipe__heading").first().text();

        // Gets the description
        String desc = doc.select("div.printedRecipe__text").first().text();

        // Gets the cook time
        String time = doc.select("div.recipe-meta-item-body").get(3).text();

        // Gets the number of servings
        String servings = doc.select("div.recipe-meta-item-body").get(4).text();

        // Gets the list of ingredients
        Elements ingredientsList = doc.select("li.ingredients-item");
        ArrayList<String> ingredients = new ArrayList<String>();
        for (Element ingredient : ingredientsList) {
            ingredients.add(ingredient.select("span.ingredients-item-name").text());
        }

        // Gets the directions
        Elements directionsList = doc.select("li.instructions-section-item");
        ArrayList<String> directions = new ArrayList<String>();
        for (Element direction : directionsList) {
            directions.add(direction.select("div.paragraph").text());
        }

        // Creates Recipe object
        RecipeDocument recipe = new RecipeDocument(name, desc, category, img, time, servings, ingredients, directions,
                fullURL);

        return recipe;
    }

    public static RecipeDocument extractFoodnetwork(String URL) {
        // Gets the page
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            System.exit(0);
        }

        // Gets the category
        Elements categories = doc.select("div.o-Capsule__m-TagList.m-TagList");
        Category category = null;
        String categoryString = null;
        for (Element c : categories) {
            categoryString = c.text();
            if (categoryString.contains("Breakfast")) {
                category = Category.BREAKFAST;
                break;
            } else if (categoryString.contains("Lunch")) {
                category = Category.LUNCH;
                break;
            } else if (categoryString.contains("Dinner")) {
                category = Category.DINNER;
                break;
            } else if (categoryString.contains("Drink")) {
                category = Category.BEVERAGES;
                break;
            } else if (categoryString.contains("Appetizer")) {
                category = Category.APPETIZERS;
                break;
            } else if (categoryString.contains("Dessert")) {
                category = Category.DESSERTS;
                break;
            } else if (categoryString.contains("Soup")) {
                category = Category.SOUPS;
                break;
            } else if (categoryString.contains("Salad")) {
                category = Category.SALADS;
                break;
            }
        }
        if (category == null) {
            category = Category.OTHER;
        }

        // // Gets an image
        Element imgs = doc.select("img.m-MediaBlock__a-Image.a-Image").first();
        String img = imgs.absUrl("src");

        // // Gets the name
        String name = doc.select("span.o-AssetTitle__a-HeadlineText").first().text();

        // // Gets the description
        Element description = doc.select("div.o-AssetDescription__a-Description").first();
        String desc = description != null ? description.text() : "";

        // // Gets the cook time
        String time = doc.select("span.m-RecipeInfo__a-Description--Total").first().text();

        // // Gets the number of servings
        String yield = doc.select("ul.o-RecipeInfo__m-Yield").first().text();
        String servings = yield.substring(yield.indexOf(": ") + 2);
        servings = servings.contains(" ") ? servings.substring(0, servings.indexOf(" ")) : servings;

        // Gets the list of ingredients
        Elements ingredientsList = doc.select("span.o-Ingredients__a-Ingredient--CheckboxLabel");
        ArrayList<String> ingredients = new ArrayList<String>();
        for (Element ingredient : ingredientsList) {
            if (ingredient.text().equals("Deselect All")) {
                continue;
            }
            ingredients.add(ingredient.text());
        }

        // // Gets the directions
        Elements directionsList = doc.select("li.o-Method__m-Step");
        ArrayList<String> directions = new ArrayList<String>();
        for (Element direction : directionsList) {
            directions.add(direction.text());
        }

        // Creates Recipe object
        RecipeDocument recipe = new RecipeDocument(name, desc, category, img, time, servings, ingredients, directions,
                URL);

        return recipe;
    }
}
