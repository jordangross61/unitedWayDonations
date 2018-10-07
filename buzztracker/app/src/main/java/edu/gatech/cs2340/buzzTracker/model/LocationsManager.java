package edu.gatech.cs2340.buzzTracker.model;
import android.util.Log;

import edu.gatech.cs2340.buzzTracker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * primary responsibility is to manage a group of DataElements
 *
 */

public class LocationsManager {

    private static LocationsManager INSTANCE = new LocationsManager();
    public static LocationsManager getInstance() { return INSTANCE; }

    List<Location> locations;

    public LocationsManager() {
        locations = new ArrayList<>();
    }

    public void addLocation(Location loc) {
        locations.add(loc);
    }

    public List<Location> getData() { return locations; }
}

