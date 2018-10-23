package edu.gatech.cs2340.buzzTracker.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.UserRights;

public class SearchActivity extends AppCompatActivity {

    private DatabaseReference locationDatabase;

    private Spinner categoryFilterSpinner;
    private Spinner locationFilterSpinner;
    private List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MYAPP", "Made it into add search activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        locationDatabase = FirebaseDatabase.getInstance().getReference().child("locations");
        locations = new ArrayList<>();

        categoryFilterSpinner = findViewById(R.id.spinner_categoryFilter);
        ArrayAdapter<UserRights> adapter_category = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemType.values());
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryFilterSpinner.setAdapter(adapter_category);

        locationFilterSpinner = findViewById(R.id.spinner_locationFilter);
        ArrayAdapter<UserRights> adapter_location = new ArrayAdapter(this,android.R.layout.simple_spinner_item, locations);
        adapter_location.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationFilterSpinner.setAdapter(adapter_location);


    }


}
