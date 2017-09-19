package com.example.caltamirano.myrecipebook.model;

import java.util.List;

/**
 * Created by caltamirano on 13/9/2017.
 */

public class RecipeBook {

    private List<Recipe> recipes;

    public RecipeBook(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
