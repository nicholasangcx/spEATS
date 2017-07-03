package com.example.speats.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.speats.Models.FoodMenu;
import com.example.speats.R;

/**
 * Created by Nicholas on 29/6/2017.
 */

public class EditMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmenu);
        Bundle recdData = getIntent().getExtras();
        FoodMenu myVal = (FoodMenu) recdData.get("food_itemName");

        TextView textView = (TextView) findViewById(R.id.editMenuHeader);
        textView.setText(myVal.getName());
    }
}
