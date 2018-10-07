package edu.gatech.cs2340.buzzTracker.controllers;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.LocationsManager;
import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;

import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        readSDFile();

    }

    /**
     * Button handler for the load button
     *
     * @param view  the actual button object that was pressed
     */
    public void onLoadButtonPressed(View view) {
        readSDFile();
        Intent intent = new Intent(this, DataItemListActivity.class);
        startActivity(intent);
    }


    private void readSDFile() {
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            LocationsManager model = LocationsManager.getInstance();

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

//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney, Australia, and move the camera.
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        for (Location loc : locations) {
//            Log.d("myTag", loc.getName());
//        }

//        final LocationManager dataService = LocationManager.getInstance();

        // get the data to display
        // iterate through the list and add a pin for each element in the model
        for (Location loc : locations) {
            LatLng location = new LatLng(loc.getLatitude(), loc.getLongitude());
            mMap.addMarker(new MarkerOptions().position(location).title(loc.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }

        // Use a custom layout for the pin data
//        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }
}