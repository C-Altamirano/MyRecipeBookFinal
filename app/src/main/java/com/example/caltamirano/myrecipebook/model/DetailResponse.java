package com.example.caltamirano.myrecipebook.model;

/**
 * Created by caltamirano on 18/9/2017.
 */

public class DetailResponse {
    private RecipeDetail recipe;

    public DetailResponse(RecipeDetail recipe) {
        this.recipe = recipe;
    }

    public RecipeDetail getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeDetail recipe) {
        this.recipe = recipe;
    }
}
