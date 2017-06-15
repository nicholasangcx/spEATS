package com.example.speats;

/**
 * Created by Nicholas on 7/6/2017.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> orderno889 = new ArrayList<String>();
        orderno889.add("India");
        orderno889.add("Pakistan");
        orderno889.add("Australia");
        orderno889.add("England");

        List<String> orderno890 = new ArrayList<String>();
        orderno890.add("Brazil");
        orderno890.add("Spain");
        orderno890.add("Germany");
        orderno890.add("Netherlands");
        orderno890.add("Italy");

        List<String> orderno891 = new ArrayList<String>();
        orderno891.add("United States");
        orderno891.add("Spain");
        orderno891.add("Argentina");
        orderno891.add("France");
        orderno891.add("Russia");

        expandableListDetail.put("Order No. 889", orderno889);
        expandableListDetail.put("Order No. 890", orderno890);
        expandableListDetail.put("Order No. 891", orderno891);
        return expandableListDetail;
    }
}