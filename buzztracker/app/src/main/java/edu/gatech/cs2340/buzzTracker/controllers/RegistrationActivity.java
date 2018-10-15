package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.google.android.gms.maps.model.Dash;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.User;
import edu.gatech.cs2340.buzzTracker.model.UserRights;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Controller for the main dashboard view of application
 */
public class RegistrationActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private Spinner rightsSpinner;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        nameField = findViewById(R.id.name_field);
        rightsSpinner = findViewById(R.id.access_spinner);

        ArrayAdapter<UserRights> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserRights.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rightsSpinner.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void onLoginOptPressed(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onRegistration(View view) {
        TextView errorMsg = findViewById(R.id.wrong_credentials_text);
        errorMsg.setText("");

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(nameField.getText().toString(), emailField.getText().toString(), passwordField.getText().toString(), (UserRights)rightsSpinner.getSelectedItem());
                            addToDatabase(user, mAuth.getUid());
                            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        } else {
                            TextView errorMsg = findViewById(R.id.wrong_credentials_text);
                            errorMsg.setText("Error");
                        }
                    }
                });
    }

    private void addToDatabase(User user, String userid) {
        mDatabase.child("users").child(userid).setValue(user);
    }
}
