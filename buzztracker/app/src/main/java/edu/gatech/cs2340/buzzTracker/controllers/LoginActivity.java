package edu.gatech.cs2340.buzzTracker.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.cs2340.buzzTracker.R;
//import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;
//import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.quickstart.auth.R;
/**
 * This is the Controller for the Login View
 */
public class LoginActivity extends AppCompatActivity {

    //persistence work for firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Button handler for pressing the login button
     *
     * @param view reference to actual button pressed
     */
    public void onLoginPressed(View view) {
        EditText emailField = findViewById(R.id.email_field);
        EditText passwordField = findViewById(R.id.password_field);

        TextView errorMsg = findViewById(R.id.wrong_credentials_text);

        errorMsg.setText("");

        mAuth.signInWithEmailAndPassword(emailField.getText().toString(), passwordField.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                } else {
                                    TextView errorMsg = findViewById(R.id.wrong_credentials_text);
                                    errorMsg.setText("Error");
                                }
                            }
                        });

    }

    public void onRegistrationOptPressed(View view){
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}
