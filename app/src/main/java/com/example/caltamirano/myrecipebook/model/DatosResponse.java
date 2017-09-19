package com.example.caltamirano.myrecipebook.model;

import java.util.List;

public class DatosResponse {

    private int count;
    private List<Recipe> recipes;

    public DatosResponse(int count, List<Recipe> recipes) {
        this.count = count;
        this.recipes = recipes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
