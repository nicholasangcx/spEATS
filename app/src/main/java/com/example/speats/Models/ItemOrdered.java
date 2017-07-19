package com.example.speats.Models;

import java.io.Serializable;

/**
 * Created by joshl on 9/6/2017.
 */

public class ItemOrdered implements Serializable{

    int qty;
    double eta;
    String name;
    double price;

    public ItemOrdered() {
    }

    public ItemOrdered(int qty, double eta, String name, double price) {
        this.qty = qty;
        this.eta = eta;
        this.name = name;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
