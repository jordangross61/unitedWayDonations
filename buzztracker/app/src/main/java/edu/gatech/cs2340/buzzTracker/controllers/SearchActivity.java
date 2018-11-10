package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.Location;

/**
 * Controller for searching the items
 */
public class SearchActivity extends AppCompatActivity {

    private EditText shortField;

    private Spinner categoryFilterSpinner;
    private Spinner locationFilterSpinner;
    private ArrayAdapter<String> adapter_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MYAPP", "Made it into add search activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        DatabaseReference locationDatabase = FirebaseDatabase.getInstance().getReference().child("locations");

        List<Object> categories = new ArrayList<>();
        categories.add("All Categories");
        categories.addAll(Arrays.asList(ItemType.values()));

        categoryFilterSpinner = findViewById(R.id.spinner_categoryFilter);

        ArrayAdapter<Object> adapter_category = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categories);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryFilterSpinner.setAdapter(adapter_category);

        shortField = findViewById(R.id.editText_search);

        locationDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("MYAPP", "Grabbing all location keys");
                ArrayList<String> locations = new ArrayList<>();
                locations.add("All Locations");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Location loc = postSnapshot.getValue(Location.class);
                    locations.add(Integer.toString(Objects.requireNonNull(loc).getKey()));
                }

                locationFilterSpinner = findViewById(R.id.spinner_locationFilter);

                if (locations.size() != 0) {
                    adapter_location = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item, locations);
                    adapter_location.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locationFilterSpinner.setAdapter(adapter_location);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError DatabaseError) {
                Log.d("MYAPP", "Retrieving specific location has an error");
            }
        });
        
    }

    /**
     * Determines what type of query to run based on the user's selection for categories and
     * locations.
     * @param view the search button
     */
    public void onSearchPressed(View view) {
        Query query;
        DatabaseReference queryDatabase = FirebaseDatabase.getInstance().getReference();

        final String category = categoryFilterSpinner.getSelectedItem().toString();
        final String location = (String)locationFilterSpinner.getSelectedItem();

        if (!shortField.getText().toString().equals("")) {
            query = queryDatabase.child("items").orderByChild("shortDescription").equalTo(shortField.getText().toString());
            runQuery(query, category, location);
        } else if (!category.equals("All Categories")) {
            query = queryDatabase.child("items").orderByChild("category").equalTo(category);
            runQuery(query, category, location);
        } else if (!location.equals("All Locations")) {
            query = queryDatabase.child("items").orderByChild("locationId").equalTo(Integer.parseInt(location));
            runQuery(query, category, location);
        } else {
            query = queryDatabase.child("items");
            runQuery(query, category, location);
        }


        //startActivity(new Intent(this, SearchResultsActivity.class));
    }

    /**
     * Finds all items that match the description and starts the activity that displays the search
     * results.
     * @param query the Firebase query based on the locations and types of items we're searching for
     * @param category the category of items we're searching for
     * @param location the location where we want the item to be located
     */
    private void runQuery(Query query, final Object category, final String location) {
        query.addValueEventListener( new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                ArrayList<Item> queriedItems = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Item item = postSnapshot.getValue(Item.class);
                    if ((location.equals("All Locations")) && (category.equals("All Categories"))) {
                        queriedItems.add(item);
                    } else if ((category.equals("All Categories")) && (Objects.requireNonNull(item).getLocationId() == Integer.parseInt(location))) {
                        queriedItems.add(item);
                    } else if ((location.equals("All Locations")) && (Objects.requireNonNull(item).getCategory().equals(category))) {
                        queriedItems.add(item);
                    } else if ((Objects.requireNonNull(item).getLocationId() == Integer.parseInt(location)) && (item.getCategory().equals(category))) {
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
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
