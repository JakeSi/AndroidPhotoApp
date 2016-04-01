package com.example.jakesi.fotagmobile;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by jakesi on 16-03-31.
 */
public class List extends Observable {
    ArrayList<Item> items = new ArrayList<Item>();
    int filter = 0;

    List() {
        setChanged();
    }

    public void addItem(Item item) {
        items.add(item);
        setChanged();
        notifyObservers();
    }

    public void clear() {
        items = new ArrayList<Item>();
        setChanged();
        notifyObservers();
    }

    public void updateRating(int rating) {
        filter = rating;
        setChanged();
        notifyObservers();
    }

    public void broadcast() {
        setChanged();
        notifyObservers();
    }
}
