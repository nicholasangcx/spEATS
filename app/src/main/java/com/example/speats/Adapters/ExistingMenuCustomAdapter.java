package com.example.speats.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.speats.Models.FoodMenu;
import com.example.speats.R;

import java.util.ArrayList;

/**
 * Created by Nicholas on 15/6/2017.
 */

public class ExistingMenuCustomAdapter extends ArrayAdapter<FoodMenu> {

    private ArrayList<FoodMenu> foodMenu;
    Context context;

    public static class ViewHolder {
        TextView name;
        TextView price;
        TextView category;
    }

    public ExistingMenuCustomAdapter(ArrayList<FoodMenu> foodMenu, Context context) {
        super(context, R.layout.existingmenu_listrow, foodMenu);
        this.foodMenu = foodMenu;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        FoodMenu foodMenu = getItem(position);
        ExistingMenuCustomAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ExistingMenuCustomAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.existingmenu_listrow, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.menuName);
            viewHolder.price = (TextView) convertView.findViewById(R.id.menuPrice);
            viewHolder.category =(TextView) convertView.findViewById(R.id.menuCategory);


            //result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ExistingMenuCustomAdapter.ViewHolder) convertView.getTag();
            //result = convertView;
        }
        viewHolder.name.setText(foodMenu.getName());
        viewHolder.price.setText(foodMenu.getPrice().toString());
        viewHolder.category.setText(foodMenu.getCategory());
        // Return the completed view to render on screen
        return convertView;
    }

    public void updateExistingMenuList() {
        notifyDataSetChanged();
    }
}
