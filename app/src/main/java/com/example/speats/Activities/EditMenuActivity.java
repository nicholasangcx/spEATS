package com.example.speats.Activities;

import android.os.Bundle;
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
    EditText foodCategory;

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
        foodCategory = (EditText) findViewById(R.id.editMenuCategoryInput);

        Button confirm = (Button) findViewById(R.id.editMenuButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = editFood.getId();
                String name = foodName.getText().toString().trim();
                String price = foodPrice.getText().toString().trim();
                String category = foodCategory.getText().toString().trim();
                updateMenu(id, name, price, category);
            }
        });
    }

    private void updateMenu (String id, String name, String price, String category) {

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(price)) {
            Toast.makeText(this, "You should enter a price", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(category)) {
            Toast.makeText(this, "You should enter a category", Toast.LENGTH_LONG).show();
        }
        else {
            FoodMenu foodMenu = new FoodMenu(id, name, price, category);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("food_menu").child(id);
            databaseReference.setValue(foodMenu);
        }
    }
}
