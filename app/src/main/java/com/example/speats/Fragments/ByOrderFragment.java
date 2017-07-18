package com.example.speats.Fragments;

/**
 * Created by Nicholas on 4/6/2017.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.speats.Adapters.CustomExpandableListAdapter;
import com.example.speats.Models.FoodOrder;
import com.example.speats.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ByOrderFragment extends Fragment {

    ExpandableListView expandableListView;
    private static CustomExpandableListAdapter expandableListAdapter;
    ArrayList<String> expandableListTitle;
    ArrayList<ArrayList<FoodOrder>> expandableListDetail;

    String restaurantName;
    DatabaseReference databaseFoodMenu;

    public static ByOrderFragment newInstance(int position) {
        ByOrderFragment fragment = new ByOrderFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    public ByOrderFragment() {

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
        //outState.putSerializable("details", expandableListDetail);
        //outState.putStringArrayList("title", expandableListTitle);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_byorder, container, false);
        restaurantName = getArguments().getString("resName");
        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child("Putera Puteri");
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
/*
        if (savedInstanceState != null) {
            expandableListDetail = (ArrayList<ArrayList<FoodOrder>>) savedInstanceState.getSerializable("details");
            expandableListTitle = savedInstanceState.getStringArrayList("title");
        }

        else {
        expandableListDetail = new ArrayList<>();
        expandableListTitle = new ArrayList<>();

        ArrayList<FoodOrder> orderno889 = new ArrayList<>();
        FoodOrder order1 = new FoodOrder("1", "Nasi Lemak", "x1", "$5.00");
        FoodOrder order2 = new FoodOrder("2", "Fries", "x1", "$3.00");
        FoodOrder order3 = new FoodOrder("3", "Iced Milo", "x1", "$2.00");
        orderno889.add(order1);
        orderno889.add(order2);
        orderno889.add(order3);
        expandableListDetail.add(orderno889);
        expandableListTitle.add("Order No. 889");

        ArrayList<FoodOrder> orderno890 = new ArrayList<>();
        FoodOrder order4 = new FoodOrder("1", "Chicken Pataya", "x1", "$5.00");
        FoodOrder order5 = new FoodOrder("2", "Prata", "x1", "$1.00");
        FoodOrder order6 = new FoodOrder("3", "Fries", "x1", "$3.00");
        FoodOrder order7 = new FoodOrder("4", "Iced Milo", "x1", "$2.00");
        orderno890.add(order4);
        orderno890.add(order5);
        orderno890.add(order6);
        orderno890.add(order7);
        expandableListDetail.add(orderno890);
        expandableListTitle.add("Order No. 890");

        ArrayList<FoodOrder> orderno891 = new ArrayList<>();
        FoodOrder order8 = new FoodOrder("1", "Prata", "x1", "$1.00");
        FoodOrder order9 = new FoodOrder("2", "Fries", "x1", "$3.00");
        FoodOrder order10 = new FoodOrder("3", "Iced Lemon Tea", "x1", "$2.00");
        orderno891.add(order8);
        orderno891.add(order9);
        orderno891.add(order10);
        expandableListDetail.add(orderno891);
        expandableListTitle.add("Order No. 891");
        }
*/

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnItemLongClickListener(new ExpandableListView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick( AdapterView<?> parent, View view, int position, long id) {

                long packedPosition = expandableListView.getExpandableListPosition(position);
                int itemType = ExpandableListView.getPackedPositionType(packedPosition);
                final int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);

        /*  if group item clicked */
                if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                    ad.setCancelable(false);
                    ad.setTitle("Warning!");
                    ad.setMessage("Only delete if order has been completed or cancelled");
                    ad.setPositiveButton(getActivity().getString(R.string.alert_delete), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            expandableListTitle.remove(groupPosition);
                            expandableListDetail.remove(groupPosition);
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

                return false;
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

                expandableListTitle.clear();
                expandableListDetail.clear();
/*
For orderMaster, get children, get key
    for orderlist (get children & get key)
 */







                for(DataSnapshot orderNoSnapShot: dataSnapshot.child("byItemMaster").getChildren()) {
                    String orderNo = orderNoSnapShot.getValue(String.class);
                    expandableListTitle.add(orderNo);
                }

                for(DataSnapshot orderDetailsSnapShot: dataSnapshot.child("byItemMaster").getChildren()) {
                    String orderNo = orderNoSnapShot.getValue(String.class);
                    expandableListTitle.add(orderNo);
                }

                expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void update() {
        expandableListAdapter.updateByOrderList();
    }
}
