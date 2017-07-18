package com.example.speats.Models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by joshl on 9/6/2017.
 */

public class DineInOrder extends Order implements Serializable{

    int numPax;


    public DineInOrder(HashMap<String,ItemOrdered> orderList, int orderNo, double eta, int numPax) {
        //super(orderList, orderNo, eta);
        this.numPax = numPax;
    }

    public int getNumPax() {
        return numPax;
    }

    public void setNumPax(int numPax) {
        this.numPax = numPax;
    }
}
