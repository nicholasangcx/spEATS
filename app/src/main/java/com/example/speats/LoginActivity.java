package com.example.speats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClick(View v) {
        //for reusablity of this method
        if (v.getId() == R.id.login) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
