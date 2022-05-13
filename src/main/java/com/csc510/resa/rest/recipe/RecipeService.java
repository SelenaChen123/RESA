package com.csc510.resa.rest.recipe;

import com.csc510.resa.document.RecipeDocument;
import com.csc510.resa.extract.RecipeExtractor;
import com.csc510.resa.document.RecipeDocument.Category;
import com.csc510.resa.model.Recipe;
import com.csc510.resa.repository.RecipeRepository;
import com.csc510.resa.repository.UserRepository;
import com.csc510.resa.rest.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public Recipe getRecipeById(String id) {
        RecipeDocument doc = recipeRepository.findById(id);
        if (doc != null && visibleByUser(doc))
            return new Recipe(doc);
        return null;
    }

    public List<Recipe> getRecipesForCurrentUser(Map<String, String> searchAndFilter) {
        List<Recipe> recipes = new ArrayList<>();

        List<RecipeDocument> recipeDocs = recipeRepository.findAll();

        for (RecipeDocument doc : recipeDocs) {
            if (visibleByUser(doc) && matchesSearchAndFilter(searchAndFilter, doc)) {
                recipes.add(new Recipe(doc));
            }
        }

        return recipes;
    }

    public Recipe extractRecipe(String url) throws MalformedURLException {
        if (!isValidURL(url)) {
            throw new MalformedURLException();
        }
        RecipeDocument doc = RecipeExtractor.extract(url);
        doc.setCreator(userService.getCurrentUser().getUsername());
        doc = recipeRepository.insert(doc);
        return new Recipe(doc);

    }

    public Recipe createRecipe(final String name, final String description, final String categoryString,
            final String imageLink, final String time, final String numServings, final ArrayList<String> ingredients,
            final ArrayList<String> instructions, final boolean isPrivate, final String originalLink) {
        Category category = null;
        if (categoryString.contains("breakfast")) {
            category = Category.BREAKFAST;
        } else if (categoryString.contains("lunch")) {
            category = Category.LUNCH;
        } else if (categoryString.contains("dinner")) {
            category = Category.DINNER;
        } else if (categoryString.contains("drink")) {
            category = Category.BEVERAGES;
        } else if (categoryString.contains("appetizer")) {
            category = Category.APPETIZERS;
        } else if (categoryString.contains("dessert")) {
            category = Category.DESSERTS;
        } else if (categoryString.contains("soup")) {
            category = Category.SOUPS;
        } else if (categoryString.contains("salad")) {
            category = Category.SALADS;
        } else {
            category = Category.OTHER;
        }

        RecipeDocument doc = new RecipeDocument(name, description, category, imageLink, time, numServings, ingredients,
                instructions, isPrivate, originalLink);
        doc.setCreator(userService.getCurrentUser().getUsername());
        doc = recipeRepository.insert(doc);
        return new Recipe(doc);
    }

    public List<Recipe> getSearchResults(Map<String, String> searchAndFilter) {
        List<Recipe> recipes = new ArrayList<>();

        List<RecipeDocument> recipeDocs = recipeRepository.findAll();

        for (RecipeDocument doc : recipeDocs) {
            if (visibleByUser(doc) && matchesSearchAndFilter(searchAndFilter, doc)) {
                recipes.add(new Recipe(doc));
            }
        }

        return recipes;
    }

    private static boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private boolean visibleByUser(RecipeDocument doc) {
        String currentUser = userService.getCurrentUser().getUsername();
        if (currentUser.equals(doc.getCreator())) {
            return true;
        } else {
            return !doc.isPrivate();
        }
    }

    private static boolean matchesSearch(String search, RecipeDocument doc) {
        if (search.equals("")) {
            return true;
        }
        String[] keywords = search.split(" ");
        for (String keyword : keywords) {
            if (doc.getName().toLowerCase().contains(keyword.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    private static boolean matchesFilter(String filter, RecipeDocument doc) {
        if (filter.equals("")) {
            return true;
        }
        String[] categories = filter.split(" ");
        for (String category : categories) {
            if (doc.getCategory().toString().equals(category)) {
                return true;
            }
        }
        return false;
    }

    private static boolean matchesSearchAndFilter(Map<String, String> searchAndFilter, RecipeDocument doc) {
        if (searchAndFilter == null) {
            return true;
        }

        String search = searchAndFilter.get("search");
        String filter = searchAndFilter.get("filter");

        if (search.equals("") && filter.equals("")) {
            return false;
        }

        return matchesSearch(search, doc) && matchesFilter(filter, doc);
    }
}
