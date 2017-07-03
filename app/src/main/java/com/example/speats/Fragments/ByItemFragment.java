package com.example.speats.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.speats.Adapters.ByItemCustomAdapter;
import com.example.speats.Models.FoodItem;
import com.example.speats.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ByItemFragment extends Fragment {

    ArrayList<FoodItem> foodItems;
    ListView listView;
    private static ByItemCustomAdapter adapter;

    public static ByItemFragment newInstance(int position) {
        ByItemFragment fragment = new ByItemFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    public ByItemFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mPosition = getArguments().getInt("position");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("foodItem", foodItems);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_byitem, container, false);

        listView=(ListView) view.findViewById(R.id.list);
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.byitem_header, listView, false);
        listView.addHeaderView(header, null, false);

        if (savedInstanceState != null) {
            foodItems = savedInstanceState.getParcelableArrayList("foodItem");
        }

        else {
            foodItems = new ArrayList<>();

            foodItems.add(new FoodItem("1", "Nasi Lemak", "1", "16:00"));
            foodItems.add(new FoodItem("2", "Chicken Pataya", "1", "16:15"));
            foodItems.add(new FoodItem("3", "Prata", "2", "16:30"));
            foodItems.add(new FoodItem("4", "Fries", "3", "16:45"));
            foodItems.add(new FoodItem("5", "Iced Milo", "2", "17:00"));
            foodItems.add(new FoodItem("6", "Iced Lemon Tea", "1", "17:15"));
        }

        adapter= new ByItemCustomAdapter(foodItems,getActivity().getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                final FoodItem item = (FoodItem) adapter.getItemAtPosition(position);
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setCancelable(false);
                ad.setTitle("Remove");
                ad.setMessage("Are you sure you want to remove item?");
                ad.setPositiveButton(getActivity().getString(R.string.alert_okay), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        foodItems.remove(item);
                        dialog.dismiss();
                        update();
                    }
                });
                ad.setNegativeButton(getActivity().getString(R.string.alert_cancel), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });

        return view;
    }

    private void update() {
        adapter.updateByItemList();
    }

}