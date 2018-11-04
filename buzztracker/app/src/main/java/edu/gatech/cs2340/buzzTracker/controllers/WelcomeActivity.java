package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.buzzTracker.R;

/**
 * Controller for the main dashboard view of application
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * Starts the activity for registering a user
     * @param view the registration button
     */
    public void onRegistrationOptPressed(View view){
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    /**
     * Starts the activity for the user to login
     * @param view the login button
     */
    public void onLoginOptPressed(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

}
