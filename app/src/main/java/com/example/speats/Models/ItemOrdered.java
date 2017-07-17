package com.example.speats.Models;

import java.io.Serializable;

/**
 * Created by joshl on 9/6/2017.
 */

public class ItemOrdered implements Serializable{

    int qty;
    MenuItem menuItem;

    public ItemOrdered() {
    }

    public ItemOrdered(int qty, MenuItem menuItem) {
        this.qty = qty;
        this.menuItem = menuItem;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        menuItem = menuItem;
    }
}
