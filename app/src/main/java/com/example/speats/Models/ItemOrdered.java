package com.example.speats.Models;

import java.io.Serializable;

/**
 * Created by joshl on 9/6/2017.
 */

public class ItemOrdered implements Serializable{

    int qty;
    double eta;
    MenuItem menuItem;

    public ItemOrdered() {
    }

    public ItemOrdered(int qty, double eta, MenuItem menuItem) {
        this.qty = qty;
        this.eta = eta;
        this.menuItem = menuItem;
    }

    public double getEta() {
        return eta;
    }

    public void setEta(double eta) {
        this.eta = eta;
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
