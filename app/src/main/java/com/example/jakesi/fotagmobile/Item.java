package com.example.jakesi.fotagmobile;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Observable;

/**
 * Created by jakesi on 16-03-31.
 */
public class Item implements Parcelable {
    private String imageUrl;
    private String resourceName;
    private int rating;

    public Item(String imageUrl, String resourceName, int rating) {
        this.imageUrl = imageUrl;
        this.resourceName = resourceName;
        this.rating = rating;
    }

    private Item(Parcel in) {
        this.imageUrl = in.readString();
        this.resourceName = in.readString();
        this.rating = in.readInt();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(resourceName);
        dest.writeInt(rating);
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
