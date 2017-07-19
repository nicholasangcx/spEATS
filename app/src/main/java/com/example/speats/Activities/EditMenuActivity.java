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

import com.example.speats.Models.MenuItem;
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
    String restaurantName;

    DatabaseReference databaseFoodMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmenu);
        Bundle recdData = getIntent().getExtras();
        final MenuItem editFood = (MenuItem) recdData.get("food_itemName");
        restaurantName = (String) recdData.get("resName");

        TextView textView = (TextView) findViewById(R.id.editMenuHeader);
        final String oldName = editFood.getItemName();
        textView.setText(oldName);

        databaseFoodMenu = FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantName);

        foodName = (EditText) findViewById(R.id.editMenuNameInput);
        foodPrice = (EditText) findViewById(R.id.editMenuPriceInput);
        foodDescription = (EditText) findViewById(R.id.editMenuDescriptionInput);
        foodPosterPath = (EditText) findViewById(R.id.editMenuPosterPathInput);

        Button confirm = (Button) findViewById(R.id.editMenuButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = editFood.getCategory();
                String name = foodName.getText().toString().trim();
                String priceString = foodPrice.getText().toString().trim();
                String description = foodDescription.getText().toString().trim();
                String posterPath = foodPosterPath.getText().toString().trim();

                if (updateMenu(oldName, name, priceString, description, posterPath, category)) {
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

    private boolean updateMenu (String oldName, String name, String priceString, String description, String posterPath, String category) {

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
            if (category.equals("Mains")) {
                MenuItem foodMenu = new MenuItem(name, price, posterPath, description, category);
                databaseFoodMenu.child("mainsMenu").child(oldName).setValue(foodMenu);
                return true;
            }
            else if (category.equals("Sides")) {
                MenuItem foodMenu = new MenuItem(name, price, posterPath, description, category);
                databaseFoodMenu.child("sidesMenu").child(oldName).setValue(foodMenu);
                return true;
            }
            else if (category.equals("Drinks")) {
                MenuItem foodMenu = new MenuItem(name, price, posterPath, description, category);
                databaseFoodMenu.child("drinksMenu").child(oldName).setValue(foodMenu);
                return true;
            }
            else {
                Toast.makeText(this, "Please enter a valid category", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
}
