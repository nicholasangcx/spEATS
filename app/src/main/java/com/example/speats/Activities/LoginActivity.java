package com.example.speats.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.speats.Models.User;
import com.example.speats.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText EmailEt, PasswordEt;
    private Button loginButton;

    //private FirebaseAuth auth;
    //private FirebaseAuth.AuthStateListener authListener;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        EmailEt = (EditText) findViewById(R.id.email);
        PasswordEt = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);

        /*
        auth = FirebaseAuth.getInstance();

        EmailEt = (EditText) findViewById(R.id.email);
        PasswordEt = (EditText) findViewById(R.id.password);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    String uid = firebaseAuth.getCurrentUser().getUid();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user_uid", uid);
                    startActivity(intent);
                }
            }
        };
        */
    }

    @Override
    protected void onStart() {
        super.onStart();

        //auth.addAuthStateListener(authListener);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final String email = EmailEt.getText().toString();
                final String password = PasswordEt.getText().toString();
                final DataSnapshot dataSnapshot1 = dataSnapshot;

                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uid = "";

                        if (email.equals("") || password.equals("")) {
                            Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_LONG).show();
                        } else {
                            boolean loginSuccess = false;
                            for (DataSnapshot userSnapShot : dataSnapshot1.getChildren()) {
                                User user = userSnapShot.getValue(User.class);
                                if (user.getUid().equals(password) && user.getName().equals(email)) {
                                    loginSuccess = true;
                                    uid = password;
                                    break;
                                }
                            }
                            if (loginSuccess) {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                Bundle mBundle = new Bundle();
                                i.putExtra("user_uid", uid);
                                i.putExtras(mBundle);
                                startActivity(i);
                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                            }

                        }

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
    public void onLoginClick(View v) {
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
    */

}
