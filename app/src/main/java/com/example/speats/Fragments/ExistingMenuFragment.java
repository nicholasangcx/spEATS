package com.example.speats.Fragments;

/**
 * Created by Nicholas on 4/6/2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.speats.Activities.EditMenuActivity;
import com.example.speats.Adapters.ExistingMenuCustomAdapter;
import com.example.speats.Models.MenuItem;
import com.example.speats.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ExistingMenuFragment extends Fragment {

    ArrayList<MenuItem> foodMenuList;
    ListView listView;
    private static ExistingMenuCustomAdapter adapter;

    DatabaseReference databaseFoodMenu;

    public static ExistingMenuFragment newInstance(int position) {
        ExistingMenuFragment fragment = new ExistingMenuFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    public ExistingMenuFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           /* mPosition = getArguments().getInt("position");*/
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
       // outState.putParcelableArrayList("foodMenu", foodMenu);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_existingmenu, container, false);
        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child("Putera Puteri");

        listView = (ListView) view.findViewById(R.id.list);
        foodMenuList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, final View v, final int position,
                                    long arg3) {
                final MenuItem item = (MenuItem) adapter.getItemAtPosition(position);
                final String category = item.getCategory();
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setCancelable(false);
                ad.setTitle("Edit");
                ad.setMessage("Edit or delete this item?");
                ad.setNegativeButton(getActivity().getString(R.string.alert_edit), new DialogInterface.OnClickListener() {
                    //edit the item, opens up new activity to edit
                    public void onClick(DialogInterface dialog, int which) {
                        MenuItem value =(MenuItem) listView.getItemAtPosition(position);
                        Intent intent = new Intent(getActivity(), EditMenuActivity.class);
                        intent.putExtra("food_itemName", value);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                ad.setPositiveButton(getActivity().getString(R.string.alert_delete), new DialogInterface.OnClickListener() {
                    //deletes this item from the food menu
                    public void onClick(DialogInterface dialog, int which) {
                        deleteFood(item.getItemName(), category);
                        dialog.dismiss();
                    }
                });
                ad.setNeutralButton(getActivity().getString(R.string.alert_cancel), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseFoodMenu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                foodMenuList.clear();

                for(DataSnapshot foodMenuSnapShot: dataSnapshot.child("mainsMenu").getChildren()) {
                    MenuItem foodMenu = foodMenuSnapShot.getValue(MenuItem.class);
                    foodMenuList.add(foodMenu);
                }
                for(DataSnapshot foodMenuSnapShot: dataSnapshot.child("sidesMenu").getChildren()) {
                    MenuItem foodMenu = foodMenuSnapShot.getValue(MenuItem.class);
                    foodMenuList.add(foodMenu);
                }
                for(DataSnapshot foodMenuSnapShot: dataSnapshot.child("drinksMenu").getChildren()) {
                    MenuItem foodMenu = foodMenuSnapShot.getValue(MenuItem.class);
                    foodMenuList.add(foodMenu);
                }

                adapter = new ExistingMenuCustomAdapter(foodMenuList, getActivity().getApplicationContext());
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void deleteFood(String id, String category) {
        DatabaseReference drFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child("Putera Puteri");

        if (category.equals("Mains")) {
            DatabaseReference ref = drFoodMenu.child("mainsMenu").child(id);
            ref.removeValue();
        }
        if (category.equals("Sides")) {
            DatabaseReference ref = drFoodMenu.child("sidesMenu").child(id);
            ref.removeValue();
        }
        if (category.equals("Drinks")) {
            DatabaseReference ref = drFoodMenu.child("drinksMenu").child(id);
            ref.removeValue();
        }
    }

    private void update() {
        adapter.updateExistingMenuList();
    }
}



