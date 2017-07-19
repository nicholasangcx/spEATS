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

import com.example.speats.Activities.MainActivity;
import com.example.speats.Adapters.ByItemCustomAdapter;
import com.example.speats.Models.ByItem;
import com.example.speats.Models.ItemOrdered;
import com.example.speats.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ByItemFragment extends Fragment {

    ArrayList<ByItem> foodItems = new ArrayList<>();
    ListView listView;
    private static ByItemCustomAdapter adapter;
    String restaurantName;
    DatabaseReference databaseFoodMenu;

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
        super.onSaveInstanceState(outState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_byitem, container, false);
        restaurantName = getArguments().getString("resName");
        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName).child("byItemMaster");
        //Set the view
        listView = (ListView) view.findViewById(R.id.list);
        //Set the header
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.byitem_header, listView, false);
        listView.addHeaderView(header, null, false);

        foodItems = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                final ByItem item = (ByItem) adapter.getItemAtPosition(position);
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setCancelable(false);
                ad.setTitle("Remove");
                ad.setMessage("Are you sure you want to remove item?");
                ad.setPositiveButton(getActivity().getString(R.string.alert_okay), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(item.getMenuItem().getItemName()+ " " + String.valueOf((int)item.getEta()));
                        dialog.dismiss();
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

    @Override
    public void onStart() {
        super.onStart();

        databaseFoodMenu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                foodItems.clear();

                for(DataSnapshot foodMenuSnapShot: dataSnapshot.getChildren()) {
                    ByItem foodMenu = foodMenuSnapShot.getValue(ByItem.class);
                    foodItems.add(foodMenu);
                }

                if (getActivity() != null)
                    adapter = new ByItemCustomAdapter(foodItems, getActivity());
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private void deleteItem(String itemOrdered) {
        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        DatabaseReference ref = databaseFoodMenu.child("byItemMaster").child(itemOrdered);
        ref.removeValue();
    }
}