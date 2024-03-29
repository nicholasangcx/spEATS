package com.example.speats.Models;

import java.io.Serializable;

/**
 * Created by joshl on 5/6/2017.
 */

public class MenuItem implements Serializable {

    private String itemName;
    private double price;
    private String posterPath;
    private String description;
    private String category;

    public MenuItem() {
    }

    public MenuItem(String itemName, double price, String posterPath, String description, String category) {
        this.itemName = itemName;
        this.price = price;
        this.posterPath = posterPath;
        this.description = description;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
