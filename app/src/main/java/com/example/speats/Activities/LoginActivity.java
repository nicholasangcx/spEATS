package com.example.speats.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.speats.Models.Restaurant;
import com.example.speats.Models.User;
import com.example.speats.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText nameEt, PasswordEt;
    private String name;
    private String password;
    private Button button;
    private Restaurant res;


    private ArrayList<User> users;
    private ArrayList<Restaurant> restaurants;

    DatabaseReference userReference;
    DatabaseReference restaurantReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        nameEt = (EditText) findViewById(R.id.name);
        PasswordEt = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.login);

        users = new ArrayList<>();
        restaurants = new ArrayList<>();

        userReference = FirebaseDatabase.getInstance().getReference("Users");
        restaurantReference = FirebaseDatabase.getInstance().getReference("Restaurants");



        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                name = nameEt.getText().toString().trim();
                password = PasswordEt.getText().toString().trim();

                boolean stat1 = false;
                boolean stat2 = false;

                for (User u : users) {
                    if (u.getUid().equals(password) && u.getName().equals(name)) {
                        stat1 = true;
                        break;
                    }
                }

                for (Restaurant r : restaurants) {
                    if (r.getName().equals(name)) {
                        res = r;
                        stat2 = true;
                        break;
                    }
                }



                if (stat1 && stat2) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("RESTAURANT", res);
                    i.putExtras(mBundle);
                    startActivity(i);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Incorrect username or password";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });


    }

    public void onStart() {
        super.onStart();
        //reading from database
        userReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot userSnapShot: dataSnapshot.getChildren()) {
                    User user = userSnapShot.getValue(User.class);
                    users.add(user);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        restaurantReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot resSnapShot: dataSnapshot.getChildren()) {
                    Restaurant res = resSnapShot.getValue(Restaurant.class);
                    restaurants.add(res);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}