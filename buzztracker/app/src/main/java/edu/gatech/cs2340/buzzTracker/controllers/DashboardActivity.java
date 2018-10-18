package edu.gatech.cs2340.buzzTracker.controllers;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;
//import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;
import edu.gatech.cs2340.buzzTracker.controllers.MapsActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Controller for the main dashboard view of application
 */
public class DashboardActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mDatabase = FirebaseDatabase.getInstance().getReference();

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
        FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(this, WelcomeActivity.class));
    }

    public void onShowMapPressed(View view) {

        readSDFile();

        startActivity(new Intent(this, MapsActivity.class));
    }

    /**
     * Button handler for the load button
     *
     * @param view  the actual button object that was pressed
     */
    public void onLocationsButtonPressed(View view) {
        readSDFile();

        startActivity(new Intent(this, LocationListActivity.class));
    }

    public void readSDFile() {
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                String[] a = line.split(",");
                Location x = new Location(Integer.parseInt(a[0]), a[1], Double.parseDouble(a[2]),
                        Double.parseDouble(a[3]), a[4], a[5], a[6], a[7], a[8], a[9], a[10]);
                mDatabase.child("locations").child(a[0]).setValue(x);
            }
            br.close();
        } catch (IOException e) {
            Log.e("MY APP", "exception: " + e.getMessage());
        }
    }
}
