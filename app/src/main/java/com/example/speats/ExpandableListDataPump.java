package com.example.speats;

/**
 * Created by Nicholas on 7/6/2017.
 */

import com.example.speats.Models.FoodOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    private static HashMap<String, List<FoodOrder>> expandableListDetail;

    public static HashMap<String, List<FoodOrder>> getData() {
        expandableListDetail = new HashMap<String, List<FoodOrder>>();

        List<FoodOrder> orderno889 = new ArrayList<FoodOrder>();
        FoodOrder order1 = new FoodOrder("1","Nasi Lemak","x1","$5.00");
        FoodOrder order2 = new FoodOrder("1","Fries","x1","$3.00");
        FoodOrder order3 = new FoodOrder("2","Iced Milo","x1","$2.00");
        orderno889.add(order1);
        orderno889.add(order2);
        orderno889.add(order3);

        List<FoodOrder> orderno890 = new ArrayList<FoodOrder>();
        FoodOrder order4 = new FoodOrder("1","Chicken Pataya","x1","$5.00");
        FoodOrder order5 = new FoodOrder("2","Prata","x1","$1.00");
        FoodOrder order6 = new FoodOrder("3","Fries","x1","$3.00");
        FoodOrder order7 = new FoodOrder("4","Iced Milo","x1","$2.00");
        orderno890.add(order4);
        orderno890.add(order5);
        orderno890.add(order6);
        orderno890.add(order7);

        List<FoodOrder> orderno891 = new ArrayList<FoodOrder>();
        FoodOrder order8 = new FoodOrder("1","Prata","x1","$1.00");
        FoodOrder order9 = new FoodOrder("2","Fries","x1","$3.00");
        FoodOrder order10 = new FoodOrder("3","Iced Lemon Tea","x1","$2.00");
        orderno891.add(order8);
        orderno891.add(order9);
        orderno891.add(order10);

        expandableListDetail.put("Order No. 889", orderno889);
        expandableListDetail.put("Order No. 890", orderno890);
        expandableListDetail.put("Order No. 891", orderno891);
        return expandableListDetail;
    }

    public static void remove(String orderNumber) {
        expandableListDetail.remove(orderNumber);
    }
}