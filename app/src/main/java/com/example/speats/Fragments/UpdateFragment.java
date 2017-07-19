package com.example.speats.Fragments;

/**
 * Created by Nicholas on 4/6/2017.
 */

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.speats.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String restaurantName;

    TextView smallNumber;
    TextView mediumNumber;
    TextView largeNumber;

    long smallNo;
    long mediumNo;
    long largeNo;

    DatabaseReference databaseReference;

    public UpdateFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance() {
        UpdateFragment fragment = new UpdateFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the byitem_header for this fragment
        View v = inflater.inflate(R.layout.fragment_update, container, false);
        restaurantName = getArguments().getString("resName");
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName).child("map");

        smallNumber = (TextView) v.findViewById(R.id.smallNumber);
        mediumNumber = (TextView) v.findViewById(R.id.mediumNumber);
        largeNumber = (TextView) v.findViewById(R.id.largeNumber);


        Button a = (Button) v.findViewById(R.id.increaseSmall);
        a.setOnClickListener(this);

        Button b = (Button) v.findViewById(R.id.decreaseSmall);
        b.setOnClickListener(this);

        Button c = (Button) v.findViewById(R.id.increaseMedium);
        c.setOnClickListener(this);

        Button d = (Button) v.findViewById(R.id.decreaseMedium);
        d.setOnClickListener(this);

        Button e = (Button) v.findViewById(R.id.increaseLarge);
        e.setOnClickListener(this);

        Button f = (Button) v.findViewById(R.id.decreaseLarge);
        f.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.increaseSmall:
                increaseSmall(smallNo);
                break;
            case R.id.decreaseSmall:
                decreaseSmall(smallNo);
                break;
            case R.id.increaseMedium:
                increaseMedium(mediumNo);
                break;
            case R.id.decreaseMedium:
                decreaseMedium(mediumNo);
                break;
            case R.id.increaseLarge:
                increaseLarge(largeNo);
                break;
            case R.id.decreaseLarge:
                decreaseLarge(largeNo);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int time = Calendar.HOUR * 100 + (Calendar.MINUTE / 15) + 600;
                String small = "small" + String.valueOf(time);
                String medium = "medium" + String.valueOf(time);
                String large = "large" + String.valueOf(time);

                for(DataSnapshot mapSnapShot: dataSnapshot.getChildren()) {

                    if (mapSnapShot.getKey().equals(small)) {
                        smallNo = mapSnapShot.getValue(Long.class);
                    }
                    if (mapSnapShot.getKey().equals(medium)) {
                        mediumNo = mapSnapShot.getValue(Long.class);
                    }
                    if (mapSnapShot.getKey().equals(large)) {
                        largeNo = mapSnapShot.getValue(Long.class);
                    }
                }

                smallNumber.setText(String.valueOf(smallNo));
                mediumNumber.setText(String.valueOf(mediumNo));
                largeNumber.setText(String.valueOf(largeNo));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void increaseSmall(long smallNo) {
        int time = Calendar.HOUR * 100 + (Calendar.MINUTE / 15) + 600;
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        DatabaseReference ref = databaseReference.child("map");
        long number = smallNo;
        number++;
        ref.child("small" + String.valueOf(time)).setValue(number);
    }

    private void decreaseSmall(long smallNo) {
        int time = Calendar.HOUR * 100 + (Calendar.MINUTE / 15) + 600;
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        DatabaseReference ref = databaseReference.child("map");
        long number = smallNo;
        number--;
        ref.child("small" + String.valueOf(time)).setValue(number);
    }

    private void increaseMedium(long mediumNo) {
        int time = Calendar.HOUR * 100 + (Calendar.MINUTE / 15) + 600;
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        DatabaseReference ref = databaseReference.child("map");
        long number = mediumNo;
        number++;
        ref.child("medium" + String.valueOf(time)).setValue(number);
    }

    private void decreaseMedium(long mediumNo) {
        int time = Calendar.HOUR * 100 + (Calendar.MINUTE / 15) + 600;
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        DatabaseReference ref = databaseReference.child("map");
        long number = mediumNo;
        number--;
        ref.child("medium" + String.valueOf(time)).setValue(number);
    }

    private void increaseLarge(long largeNo) {
        int time = Calendar.HOUR * 100 + (Calendar.MINUTE / 15) + 600;
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        DatabaseReference ref = databaseReference.child("map");
        long number = largeNo;
        number++;
        ref.child("large" + String.valueOf(time)).setValue(number);
    }

    private void decreaseLarge(long largeNo) {
        int time = Calendar.HOUR * 100 + (Calendar.MINUTE / 15) + 600;
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);
        DatabaseReference ref = databaseReference.child("map");
        long number = largeNo;
        number--;
        ref.child("large" + String.valueOf(time)).setValue(number);
    }

}





