package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;
import edu.gatech.cs2340.buzzTracker.model.User;

/**
 * Controller for the main dashboard view of application
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onRegistrationOptPressed(View view){
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void onLoginOptPressed(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

}
