package com.example.speats;

/**
 * Created by Nicholas on 7/6/2017.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.speats.Models.FoodOrder;

import java.util.ArrayList;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> expandableListTitle;
    private ArrayList<ArrayList<FoodOrder>>  expandableListDetail;

    private static class ViewHolder {
        TextView sn;
        TextView name;
        TextView quantity;
        TextView price;
    }

    public CustomExpandableListAdapter(Context context, ArrayList<String> expandableListTitle,
                                       ArrayList<ArrayList<FoodOrder>> expandableListDetail) {
        this.context = context;
        this.expandableListDetail = expandableListDetail;
        this.expandableListTitle = expandableListTitle;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(listPosition).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final FoodOrder foodOrder = (FoodOrder) getChild(listPosition, expandedListPosition);
        ViewHolder viewHolder;

        viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.byorder_listitem, null);
        viewHolder.sn = (TextView) convertView.findViewById(R.id.byorder_sn);
        viewHolder.name = (TextView) convertView.findViewById(R.id.byorder_name);
        viewHolder.quantity = (TextView) convertView.findViewById(R.id.byorder_quantity);
        viewHolder.price = (TextView) convertView.findViewById(R.id.byorder_price);

        viewHolder.sn.setText(foodOrder.getSn());
        viewHolder.name.setText(foodOrder.getName());
        viewHolder.quantity.setText(foodOrder.getQuantity());
        viewHolder.price.setText(foodOrder.getPrice());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(listPosition).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.byorder_listgroup, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    public void updateByOrderList() {
        notifyDataSetChanged();
    }

}