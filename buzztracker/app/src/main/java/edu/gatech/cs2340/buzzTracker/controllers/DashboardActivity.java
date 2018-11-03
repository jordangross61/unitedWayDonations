package edu.gatech.cs2340.buzzTracker.controllers;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import com.google.firebase.database.DatabaseReference;

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

    }

    /**
     * Logs out of the user's account and returns to the welcome page
     *
     * @param view the logout button
     */
    public void onLogoutPressed(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, WelcomeActivity.class));
    }

    /**
     * Starts the activity for displaying the map
     *
     * @param view the Show Map button
     */
    public void onShowMapPressed(View view) {
        //readSDFile();
        startActivity(new Intent(this, MapsActivity.class));
    }

    /**
     * Starts the activity for displaying the list of all the donation center locations
     *
     * @param view the Show Locations button
     */
    public void onLocationsButtonPressed(View view) {
        //readSDFile();
        startActivity(new Intent(this, LocationListActivity.class));
    }

    /**
     * Starts the activity for searching the list of items
     *
     * @param view the Search Items button
     */
    public void onSearchItemsPressed(View view) {
        //readSDFile();
        startActivity(new Intent(this, SearchActivity.class));
    }

    /**
     * Reads the SD file that contains the data for all the locations and adds those locations to
     * the database
     */
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
            Log.e("MY APP", "unable to read in locations");
        }
    }
}
