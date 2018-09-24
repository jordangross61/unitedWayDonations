package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;

/**
 * This is the Controller for the Login View
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Button handler for pressing the login button
     *
     * @param view reference to actual button pressed
     */
    public void onLoginPressed(View view) {
        EditText usernameField = findViewById(R.id.username_field);
        EditText passwordField = findViewById(R.id.password_field);
        TextView errorMsg = findViewById(R.id.wrong_credentials_text);

        errorMsg.setText("");

        //get a reference to the model
        LoginServiceFacade model = LoginServiceFacade.getInstance();

        //check the password and user id
        if (model.doLogin(usernameField.getText().toString(), passwordField.getText().toString())) {
            //good login go to the Dashboard screen
            startActivity(new Intent(this, DashboardActivity.class));
        } else {
            usernameField.setText("");
            passwordField.setText("");
            errorMsg.setText("Username/Password incorrect. Try again.");
        }
    }

    public void onRegistrationOptPressed(View view){
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}
