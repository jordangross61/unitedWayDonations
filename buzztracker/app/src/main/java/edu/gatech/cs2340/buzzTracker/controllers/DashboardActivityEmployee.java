package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.User;

public class DashboardActivityEmployee extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Location myLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_employee);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myLocation = (Location) bundle.getSerializable("Location");
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
    /**
     * Button handler for the Logout button
     *
     * @param view actual reference for the button pressed
     */
    public void onLogoutPressed(View view) {
        FirebaseAuth.getInstance().signOut();
        mDatabase.child("locations").child(Integer.toString(myLocation.getKey())).setValue(myLocation);
        startActivity(new Intent(this, WelcomeActivity.class));
    }

    public void onShowDataPressed(View view) {
        mDatabase.child("locations").child(Integer.toString(myLocation.getKey())).setValue(myLocation);
        Intent i=new Intent(getApplicationContext(), AddCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Location", myLocation);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void onShowListPressed(View view) {
        mDatabase.child("locations").child(Integer.toString(myLocation.getKey())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("MYAPP", "Grabbing the specific data at a location");
                Location loc = snapshot.getValue(Location.class);

                ArrayList<Item> items = loc.getItemList();
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
