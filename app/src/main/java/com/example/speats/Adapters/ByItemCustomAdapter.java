package com.example.speats.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.speats.Models.FoodItem;
import com.example.speats.R;

import java.util.ArrayList;

/**
 * Created by Nicholas on 13/6/2017.
 */

public class    ByItemCustomAdapter extends ArrayAdapter<FoodItem> {

    private ArrayList<FoodItem> foodItems;
    Context context;

    private static class ViewHolder {
        TextView sn;
        TextView foodName;
        TextView quantity;
        TextView time;
    }

    public ByItemCustomAdapter(ArrayList<FoodItem> foodItems, Context context) {
        super(context, R.layout.byitem_listrow, foodItems);
        this.foodItems = foodItems;
        this.context = context;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        FoodItem foodItems = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.byitem_listrow, parent, false);
            viewHolder.sn = (TextView) convertView.findViewById(R.id.sn);
            viewHolder.foodName = (TextView) convertView.findViewById(R.id.foodName);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.foodQuantity);
            viewHolder.time = (TextView) convertView.findViewById(R.id.foodTime);

            //result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            //result = convertView;
        }
        int number = position + 1;
        viewHolder.sn.setText("" + number);
        viewHolder.foodName.setText(foodItems.getName());
        viewHolder.quantity.setText(foodItems.getQuantity());
        viewHolder.time.setText(foodItems.getTime());
        // Return the completed view to render on screen
        return convertView;
    }

    public void updateByItemList() {
        notifyDataSetChanged();
    }

}
