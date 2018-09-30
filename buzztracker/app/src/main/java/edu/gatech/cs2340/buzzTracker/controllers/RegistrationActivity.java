package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;


import com.google.android.gms.maps.model.Dash;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;
import edu.gatech.cs2340.buzzTracker.model.UserManager;
import edu.gatech.cs2340.buzzTracker.model.UserRights;

/**
 * Controller for the main dashboard view of application
 */
public class RegistrationActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private Spinner rightsSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void onLoginOptPressed(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }
//    public void onRegistration(View view) {
//        emailField = findViewById(R.id.email_field);
//        passwordField = findViewById(R.id.password_field);
//        nameField = findViewById(R.id.name_field);
//
//        TextView errorMsg = findViewById(R.id.wrong_credentials_text);
//
//        errorMsg.setText("");
//
//        //get a reference to the user managers
//        LoginServiceFacade model = LoginServiceFacade.getInstance();
//
//        if (users.addUser(nameField.getText().toString(), emailField.getText().toString(),
//                passwordField.getText().toString())) {
//
//            startActivity(new Intent(this, DashboardActivity.class));
//
//        } else {
//            emailField.setText("");
//            passwordField.setText("");
//            errorMsg.setText("Username/Password exists. Try again.");
//        }
//    }

    public void onRegistration(View view) {
        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        nameField = findViewById(R.id.name_field);

        TextView errorMsg = findViewById(R.id.wrong_credentials_text);

        errorMsg.setText("");

        //get a reference to the model
        LoginServiceFacade model = LoginServiceFacade.getInstance();

        UserManager users = model.getUserManager();

        if (users.addUser(nameField.getText().toString(), emailField.getText().toString(),
                passwordField.getText().toString())) {

            startActivity(new Intent(this, DashboardActivity.class));

        } else {
            emailField.setText("");
            passwordField.setText("");
            errorMsg.setText("Username/Password exists. Try again.");
        }
    }

}
