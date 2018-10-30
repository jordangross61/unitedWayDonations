package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.Size;

public class SearchActivity extends AppCompatActivity {

    private DatabaseReference locationDatabase;
    private EditText shortField;

    private Spinner categoryFilterSpinner;
    private Spinner locationFilterSpinner;
    private ArrayAdapter<String> adapter_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MYAPP", "Made it into add search activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        locationDatabase = FirebaseDatabase.getInstance().getReference().child("locations");

        List<Object> categories = new ArrayList<>();
        categories.add("All Categories");
        for( ItemType s : ItemType.values()) {
            categories.add(s);
        }

        categoryFilterSpinner = findViewById(R.id.spinner_categoryFilter);
        ArrayAdapter<Object> adapter_category = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categories);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryFilterSpinner.setAdapter(adapter_category);

        shortField = findViewById(R.id.editText_search);



        locationDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("MYAPP", "Grabbing all location keys");
                ArrayList<String> locations = new ArrayList<String>();
                locations.add("All Locations");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Location loc = postSnapshot.getValue(Location.class);
                    locations.add(Integer.toString(loc.getKey()));
                }

                locationFilterSpinner = findViewById(R.id.spinner_locationFilter);
                adapter_location = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item, locations);
                adapter_location.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                locationFilterSpinner.setAdapter(adapter_location);
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
                Log.d("MYAPP", "Retrieving specific location has an error");
            }
        });
        
    }

    public void onSearchPressed(View view) {
        Query query;
        DatabaseReference queryDatabase = FirebaseDatabase.getInstance().getReference();

        final Object category = categoryFilterSpinner.getSelectedItem();
        final String location = (String)locationFilterSpinner.getSelectedItem();

        query = queryDatabase.child("items").orderByChild("shortDescription").equalTo(shortField.getText().toString());
        query.addValueEventListener( new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                ArrayList<Item> queriedItems = new ArrayList<Item>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Item item = postSnapshot.getValue(Item.class);
                    if ((location.equals("All Locations")) && (category.equals("All Categories"))) {
                        queriedItems.add(item);
                    } else if ((category.equals("All Categories")) && (item.getLocationId() == Integer.parseInt(location))) {
                        queriedItems.add(item);
                    } else if ((location.equals("All Locations")) && (item.getCategory().equals((ItemType)category))) {
                        queriedItems.add(item);
                    } else if ((item.getLocationId() == Integer.parseInt(location)) && (item.getCategory().equals((ItemType)category))) {
                        queriedItems.add(item);
                    }
                }
                Intent i = new Intent(getApplicationContext(), SearchResultsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("QueriedItems", queriedItems);
                i.putExtras(bundle);
                startActivity(i);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
        //startActivity(new Intent(this, SearchResultsActivity.class));
    }

}