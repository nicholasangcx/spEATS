package com.example.speats.Models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by joshl on 9/6/2017.
 */

public class Order implements Serializable{

    HashMap<String,ItemOrdered> orderList;
    int orderNo;
    double eta;
    int numPax;

    public Order() {
    }

    public Order(HashMap<String,ItemOrdered> orderList, int orderNo, double eta, int numPax) {
        this.orderList = orderList;
        this.orderNo = orderNo;
        this.eta = eta;
        this.numPax = numPax;
    }

    public int getNumPax() {
        return numPax;
    }

    public HashMap<String,ItemOrdered> getOrderList() {
        return orderList;
    }

    public void setOrderList(HashMap<String,ItemOrdered> orderList) {
        this.orderList = orderList;
    }


    public int getOrderNo() {
        return orderNo;
    }


    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }


    public double getEta() {
        return eta;
    }

    public void setEta(double eta) {
        this.eta = eta;
    }

    public boolean containsOrder(MenuItem item) {
        return orderList.containsKey(item.getItemName());
    }

    public void changeQuantity(MenuItem item, int qty) {
        orderList.get(item.getItemName()).setQty(qty);

    }

    public int getQuantity(MenuItem item) {
        if (containsOrder(item)) {
            return orderList.get(item.getItemName()).getQty();
        } else {
            return 0;
        }
    }
}
