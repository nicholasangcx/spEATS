package com.example.speats.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.speats.Processors.BackgroundWorker;
import com.example.speats.R;

public class LoginActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameEt = (EditText) findViewById(R.id.username);
        PasswordEt = (EditText) findViewById(R.id.password);
    }

    public void onLoginClick(View v) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
        //for reusablity of this method
        /*if (v.getId() == R.id.login) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        */
    }

}
