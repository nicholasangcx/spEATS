package com.example.speats.Models;

/**
 * Created by Nicholas on 13/6/2017.
 */

public class FoodOrder {

    String sn;
    String name;
    String quantity;
    String price;

    public FoodOrder(String sn, String name, String quantity, String price) {
        this.sn = sn;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSn() {
        return sn;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
