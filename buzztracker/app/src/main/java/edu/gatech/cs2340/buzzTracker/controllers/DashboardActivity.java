package edu.gatech.cs2340.buzzTracker.controllers;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.LocationsManager;
import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;
import edu.gatech.cs2340.buzzTracker.controllers.MapsActivity;

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

    public void onShowMapPressed(View view) {
        LocationsManager model = LocationsManager.getInstance();

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

        startActivity(new Intent(this, DataItemListActivity.class));
    }

    public void readSDFile() {
        LocationsManager model = LocationsManager.getInstance();
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
                model.addLocation((new Location(Integer.parseInt(a[0]), a[1], Double.parseDouble(a[2]),
                        Double.parseDouble(a[3]), a[4], a[5], a[6], a[7], a[8], a[9], a[10])));
            }
            br.close();
        } catch (IOException e) {
            Log.e("MY APP", "exception: " + e.getMessage());
        }
    }
}
