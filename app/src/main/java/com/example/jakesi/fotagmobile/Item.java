package com.example.jakesi.fotagmobile;

import java.util.Observable;

/**
 * Created by jakesi on 16-03-31.
 */
public class Item extends Observable {
    private String imageUrl;
    private String title;
    private int rating;

    public Item(String imageUrl, String title, int rating) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
