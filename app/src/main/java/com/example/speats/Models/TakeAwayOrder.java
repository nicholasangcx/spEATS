package com.example.speats.Models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by joshl on 9/6/2017.
 */

public class TakeAwayOrder extends Order implements Serializable {

    public TakeAwayOrder(){

    }

    public TakeAwayOrder(HashMap<String,ItemOrdered> orderList, int orderNo, double eta) {
        super(orderList, orderNo, eta);
    }
}
