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
import com.example.speats.Models.ByItem;
import com.example.speats.Models.ItemOrdered;
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
    ArrayList<ArrayList<ItemOrdered>> expandableListDetail;

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
        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);

        expandableListTitle = new ArrayList<>();
        expandableListDetail = new ArrayList<>();

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
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                long packedPosition = expandableListView.getExpandableListPosition(position);
                int itemType = ExpandableListView.getPackedPositionType(packedPosition);
                int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                final String orderNo = expandableListTitle.get(groupPosition);

        /*  if group item clicked */
                if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                    ad.setCancelable(false);
                    ad.setTitle("Warning!");
                    ad.setMessage("Only delete if order has been completed or cancelled");
                    ad.setPositiveButton(getActivity().getString(R.string.alert_delete), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            deleteOrder(orderNo);
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

                for (DataSnapshot orderNoSnapShot : dataSnapshot.child("orderMaster").getChildren()) {
                    String orderNo = orderNoSnapShot.getKey();
                    expandableListTitle.add(orderNo);

                    ArrayList<ItemOrdered> foodOrderList = new ArrayList<>();
                    for (DataSnapshot orderListSnapShot : orderNoSnapShot.child("orderList").getChildren()) {
                        //String itemName = orderListSnapShot.getKey();
                        ItemOrdered byOrder = orderListSnapShot.getValue(ItemOrdered.class);
                        foodOrderList.add(byOrder);
                    }
                    expandableListDetail.add(foodOrderList);
                }
                if (getActivity()  != null)
                    expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void deleteOrder(String orderNo) {
        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        DatabaseReference ref = databaseFoodMenu.child("orderMaster").child(orderNo);
        ref.removeValue();
    }
}
