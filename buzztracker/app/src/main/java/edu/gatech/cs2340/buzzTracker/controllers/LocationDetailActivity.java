package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;

/**
 * An activity representing a single DataItem detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link DataItemListActivity}.
 */
public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

                int item_id = getIntent().getIntExtra(LocationDetailFragment.ARG_ITEM_ID, 1000) + 1;
                String locationid = Integer.toString(item_id);
                Log.d("MYAPP", "populating fragment with id: " + locationid);
                //locationid = "1";
                FirebaseDatabase.getInstance().getReference().child("locations").child(locationid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get Post object and use the values to update the UI
                        Log.d("MYAPP", "creating fragment");
                        Location mItem = dataSnapshot.getValue(Location.class);
                        Bundle arguments = new Bundle();
                        arguments.putSerializable(LocationDetailFragment.ARG_ITEM_ID, mItem);
                        LocationDetailFragment fragment = new LocationDetailFragment();
                        //fragment.onCreate(arguments);
                        Log.d("MYAPP", "launching fragment");
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.dataitem_detail_container, fragment)
                                .disallowAddToBackStack()
                                .commit();
                        fragment.updateLocation(mItem);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        // [START_EXCLUDE]
                        int x = 0;
                        Log.d("MYAPP", "Retrieving from database has error");
                        // [END_EXCLUDE]
                    }
                });

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, LocationListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
