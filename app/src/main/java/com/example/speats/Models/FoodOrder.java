package com.example.speats.Models;

import java.io.Serializable;

/**
 * Created by Nicholas on 13/6/2017.
 */

public class FoodOrder implements Serializable {

    //String name;
    private MenuItem menuItem;
    private int quantity;
    //double price;

    public FoodOrder(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }


    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

}