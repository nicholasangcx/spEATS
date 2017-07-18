package com.example.speats.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.speats.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText EmailEt, PasswordEt;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        EmailEt = (EditText) findViewById(R.id.email);
        PasswordEt = (EditText) findViewById(R.id.password);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    String uid = firebaseAuth.getCurrentUser().getUid();
                    //getName(uid);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user_id", uid);
                    startActivity(intent);
                }
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        };

    }
/*
    private void getName(final String userid) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapShot: dataSnapshot.getChildren()) {
                    User user = userSnapShot.getValue(User.class);
                    if (user.getUid().equals(userid)) {
                        restaurantName = user.getName();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
*/
    @Override
    protected void onStart() {
        super.onStart();

        auth.addAuthStateListener(authListener);
    }


    public void onLoginClick(View v)  {
        //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        userLogin();
    }

    private void userLogin() {
        String email = EmailEt.getText().toString();
        String password = PasswordEt.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_LONG).show();
        }
        else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
