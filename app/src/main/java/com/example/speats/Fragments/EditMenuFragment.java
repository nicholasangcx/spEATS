package com.example.speats.Fragments;

/**
 * Created by Nicholas on 4/6/2017.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speats.Models.MenuItem;
import com.example.speats.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditMenuFragment extends Fragment implements View.OnClickListener{

    EditText foodName;
    EditText foodPrice;
    EditText foodCat;
    EditText foodDescription;
    EditText foodPosterPath;
    String restaurantName;

    DatabaseReference databaseFoodMenu;

    public static EditMenuFragment newInstance(int position) {
        EditMenuFragment fragment = new EditMenuFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    public EditMenuFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           /* mPosition = getArguments().getInt("position");*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editmenu, container, false);
        restaurantName = getArguments().getString("resName");
        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);

        //For testing
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(restaurantName);

        foodName = (EditText) view.findViewById(R.id.addNewFoodName);
        foodPrice = (EditText) view.findViewById(R.id.addNewFoodPrice);
        foodCat = (EditText) view.findViewById(R.id.addNewFoodCat);
        foodDescription = (EditText) view.findViewById(R.id.addNewFoodDescription);
        foodPosterPath = (EditText) view.findViewById(R.id.addNewFoodPosterPath);

        Button addNew = (Button) view.findViewById(R.id.menu_addNew);
        addNew.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.menu_addNew:
                addFood();
        }
    }

    private void addFood() {
        String name = foodName.getText().toString().trim();
        String priceString = foodPrice.getText().toString().trim();
        String category = foodCat.getText().toString().trim();
        String description = foodDescription.getText().toString().trim();
        String posterPath = foodPosterPath.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this.getActivity(), "You should enter a name", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(priceString)) {
            Toast.makeText(this.getActivity(), "You should enter a price", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(category)) {
            Toast.makeText(this.getActivity(), "You should enter a category", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(description)) {
            Toast.makeText(this.getActivity(), "You should enter a description", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(posterPath)) {
            Toast.makeText(this.getActivity(), "You should enter an image url", Toast.LENGTH_LONG).show();
        }
        else {
            Double price = Double.valueOf(foodPrice.getText().toString());
            if (category.equals("Mains")) {
                MenuItem foodMenu = new MenuItem(name, price, posterPath, description, category);
                databaseFoodMenu.child("mainsMenu").child(name).setValue(foodMenu);
                update();
            }
            else if (category.equals("Sides")) {
                MenuItem foodMenu = new MenuItem(name, price, posterPath, description, category);
                databaseFoodMenu.child("sidesMenu").child(name).setValue(foodMenu);
                update();
            }
            else if (category.equals("Drinks")) {
                MenuItem foodMenu = new MenuItem(name, price, posterPath, description, category);
                databaseFoodMenu.child("drinksMenu").child(name).setValue(foodMenu);
                update();
            }
            else {
                Toast.makeText(this.getActivity(), "Please enter a valid category", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void update() {
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setCancelable(false);
        ad.setTitle("Success!");
        ad.setMessage("Item added to menu");
        ad.setPositiveButton(getActivity().getString(R.string.alert_okay), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }
}
