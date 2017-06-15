package com.example.speats.Models;

/**
 * Created by Nicholas on 15/6/2017.
 */

public class FoodMenu {

    String name;
    String price;

    public FoodMenu(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }

    public String getPrice() { return price; }
}
