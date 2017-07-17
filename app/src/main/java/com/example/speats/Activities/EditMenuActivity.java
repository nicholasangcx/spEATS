package com.example.speats.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speats.Models.FoodMenu;
import com.example.speats.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nicholas on 29/6/2017.
 */

public class EditMenuActivity extends AppCompatActivity {

    EditText foodName;
    EditText foodPrice;
    EditText foodDescription;
    EditText foodPosterPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmenu);
        Bundle recdData = getIntent().getExtras();
        final FoodMenu editFood = (FoodMenu) recdData.get("food_itemName");

        TextView textView = (TextView) findViewById(R.id.editMenuHeader);
        textView.setText(editFood.getName());


        foodName = (EditText) findViewById(R.id.editMenuNameInput);
        foodPrice = (EditText) findViewById(R.id.editMenuPriceInput);
        foodDescription = (EditText) findViewById(R.id.editMenuDescriptionInput);
        foodPosterPath = (EditText) findViewById(R.id.editMenuPosterPathInput);

        Button confirm = (Button) findViewById(R.id.editMenuButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editFood.getId();
                String category = editFood.getCategory();
                String name = foodName.getText().toString().trim();
                String priceString = foodPrice.getText().toString().trim();
                String description = foodDescription.getText().toString().trim();
                String posterPath = foodPosterPath.getText().toString().trim();
                if (updateMenu(id, name, priceString, description, posterPath, category)) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(EditMenuActivity.this);
                    ad.setCancelable(false);
                    ad.setTitle("Success!");
                    ad.setMessage("Item has been updated");

                    ad.setNeutralButton(getString(R.string.alert_okay), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            onBackPressed();
                        }
                    });
                    ad.show();
                }
            }
        });
    }

    private boolean updateMenu (String id, String name, String priceString, String description, String posterPath, String category) {

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (TextUtils.isEmpty(priceString)) {
            Toast.makeText(this, "You should enter a price", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (TextUtils.isEmpty(description)) {
            Toast.makeText(this, "You should enter a description", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (TextUtils.isEmpty(posterPath)) {
            Toast.makeText(this, "You should enter an image url", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            Double price = Double.valueOf(foodPrice.getText().toString());
            FoodMenu foodMenu = new FoodMenu(id, name, price, description, posterPath, category);
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child("Putera Puteri");
            if (category.equals("Mains")) {
                DatabaseReference ref = databaseReference.child("mainsMenu").child(id);
                ref.setValue(foodMenu);
            }
            if (category.equals("Sides")) {
                DatabaseReference ref = databaseReference.child("sidesMenu").child(id);
                ref.setValue(foodMenu);
            }
            if (category.equals("Drinks")) {
                DatabaseReference ref = databaseReference.child("drinksMenu").child(id);
                ref.setValue(foodMenu);
            }

            return true;
        }
    }
}
