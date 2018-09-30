package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;
import edu.gatech.cs2340.buzzTracker.model.User;

/**
 * Controller for the main dashboard view of application
 */
public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        User user = LoginServiceFacade.getInstance().getCurrentUser();
//        setTitle("Welcome back, " + user.getName());
    }

    /**
     * Button handler for the Logout button
     *
     * @param view actual reference for the button pressed
     */
    public void onLogoutPressed(View view) {
        LoginServiceFacade model = LoginServiceFacade.getInstance();
        model.logout();

        startActivity(new Intent(this, WelcomeActivity.class));
    }
}
