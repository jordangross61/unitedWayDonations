package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivityEmployee extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_employee);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myLocation = (Location) Objects.requireNonNull(bundle).getSerializable("Location");
        mDatabase = FirebaseDatabase.getInstance().getReference();

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

    /**
     * Starts the activity for adding an item to the location's list of items
     *
     * @param view the Add Donation Item button
     */
    public void onShowDataPressed(View view) {
        mDatabase.child("locations").child(Integer.toString(myLocation.getKey()))
                .setValue(myLocation);
        Intent i = new Intent(getApplicationContext(), AddCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Location", myLocation);
        i.putExtras(bundle);
        startActivity(i);
    }

    /**
     * Retrieves the data for all the locations and starts the activity for displaying the data
     *
     * @param view the Show List of Items button
     */
    public void onShowListPressed(View view) {
        mDatabase.child("locations").child(Integer.toString(myLocation.getKey()))
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("MYAPP", "Grabbing the specific data at a location");
                Location loc = snapshot.getValue(Location.class);

                ArrayList<Item> items = Objects.requireNonNull(loc).getItemList();
                Intent i = new Intent(getApplicationContext(), DataItemListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ItemList", items);
                i.putExtras(bundle);
                startActivity(i);
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
                Log.d("MYAPP", "Retrieving specific location has an error");
            }
        });
    }
}
