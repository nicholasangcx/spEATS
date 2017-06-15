package com.example.speats.Models;

/**
 * Created by Nicholas on 13/6/2017.
 */

public class FoodItem {

    String sn;
    String name;
    String quantity;
    String time;

    public FoodItem(String sn, String name, String quantity, String time) {
        this.sn = sn;
        this.name = name;
        this.quantity = quantity;
        this.time = time;
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

    public String getTime() {
        return time;
    }
}
