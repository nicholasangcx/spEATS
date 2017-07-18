package com.example.speats.Models;

import java.io.Serializable;

/**
 * Created by joshl on 17/7/2017.
 */

public class ByItem implements Serializable {

    private MenuItem menuItem;
    private int qty;
    private double eta;

    public ByItem() {
    }

    public ByItem(MenuItem menuItem, int qty, double eta) {
        this.menuItem = menuItem;
        this.qty = qty;
        this.eta = eta;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getEta() {
        return eta;
    }

    public void setEta(double eta) {
        this.eta = eta;
    }
}
