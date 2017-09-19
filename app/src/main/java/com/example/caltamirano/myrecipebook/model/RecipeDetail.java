package com.example.caltamirano.myrecipebook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by caltamirano on 13/9/2017.
 */

public class RecipeDetail {
    @SerializedName("recipe_id")
    private String id;
    private String publisher;
    @SerializedName("f2f_url")
    private String fUrl;
    private String title;
    @SerializedName("source_url")
    private String sourceUrl;
    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("social_rank")
    private float socialRank;

    @SerializedName("publisher_url")
    private String publisherUrl;
    private List<String> ingredients;

    public RecipeDetail(String id, String publisher, String fUrl, String title, String sourceUrl, String imageUrl, float socialRank, String publisherUrl, List<String> ingredients) {
        this.id = id;
        this.publisher = publisher;
        this.fUrl = fUrl;
        this.title = title;
        this.sourceUrl = sourceUrl;
        this.imageUrl = imageUrl;
        this.socialRank = socialRank;
        this.publisherUrl = publisherUrl;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getfUrl() {
        return fUrl;
    }

    public void setfUrl(String fUrl) {
        this.fUrl = fUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getSocialRank() {
        return socialRank;
    }

    public void setSocialRank(float socialRank) {
        this.socialRank = socialRank;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public void setPublisherUrl(String publisherUrl) {
        this.publisherUrl = publisherUrl;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
