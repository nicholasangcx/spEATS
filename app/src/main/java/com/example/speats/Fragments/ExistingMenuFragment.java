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
import com.example.speats.Models.FoodMenu;
import com.example.speats.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ExistingMenuFragment extends Fragment {

    ArrayList<FoodMenu> foodMenuList;
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
        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("food_menu");

        listView = (ListView) view.findViewById(R.id.list);
        foodMenuList = new ArrayList<>();
        /*
        //Replace by data from the database
        if (savedInstanceState != null) {
            foodMenu = savedInstanceState.getParcelableArrayList("foodMenu");
        }

        else {

            foodMenu = new ArrayList<>();

            foodMenu.add(new FoodMenu("1","Nasi Goreng Kampung", "$5.00","blah"));
            foodMenu.add(new FoodMenu("2","Nasi Goreng Pataya", "$5.00","blah"));
            foodMenu.add(new FoodMenu("3","Nasi Goreng Ayam", "$5.00","blah"));
            foodMenu.add(new FoodMenu("4","Nasi Goreng USA", "$5.00","blah"));
            foodMenu.add(new FoodMenu("5","Nasi Goreng Cina", "$5.00","blah"));
            foodMenu.add(new FoodMenu("6","Nasi Lemak Ayam", "$7.00","blah"));
            foodMenu.add(new FoodMenu("7","Cheese Fries", "$2.00","blah"));
        }

        adapter = new ExistingMenuCustomAdapter(foodMenuList, getActivity().getApplicationContext());

        listView.setAdapter(adapter);
*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, final View v, final int position,
                                    long arg3) {
                final FoodMenu item = (FoodMenu) adapter.getItemAtPosition(position);
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setCancelable(false);
                ad.setTitle("Edit");
                ad.setMessage("Edit or delete this item?");
                ad.setNegativeButton(getActivity().getString(R.string.alert_edit), new DialogInterface.OnClickListener() {
                    //edit the item, opens up new activity to edit
                    public void onClick(DialogInterface dialog, int which) {
                        FoodMenu value =(FoodMenu) listView.getItemAtPosition(position);
                        Intent intent = new Intent(getActivity(), EditMenuActivity.class);
                        intent.putExtra("food_itemName", value);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                ad.setPositiveButton(getActivity().getString(R.string.alert_delete), new DialogInterface.OnClickListener() {
                    //deletes this item from the food menu
                    public void onClick(DialogInterface dialog, int which) {
                        foodMenuList.remove(item);
                        dialog.dismiss();
                        update();
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

                for(DataSnapshot foodMenuSnapShot: dataSnapshot.getChildren()) {
                    FoodMenu foodMenu = foodMenuSnapShot.getValue(FoodMenu.class);
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

    private void update() {
        adapter.updateExistingMenuList();
    }
}



