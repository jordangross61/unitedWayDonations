package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;

/**
 * Controller for the add item view of application
 */
public class AddItemActivity extends AppCompatActivity {

    private EditText shortField;
    private EditText longField;
    private EditText valueField;
    private EditText commentField;
    private Location myLocation;
    private DatabaseReference mDatabase;
    private DatabaseReference lDatabase;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        shortField = findViewById(R.id.short_field);
        longField = findViewById(R.id.long_field);
        valueField = findViewById(R.id.value_field);
        commentField = findViewById(R.id.comments_field);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myLocation = (Location) bundle.getSerializable("Location");
        category = (String) bundle.getSerializable("Category");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("items");
        lDatabase = FirebaseDatabase.getInstance().getReference().child("locations");
        Log.d("MYAPP", "Into the Add Item Activity Page");
    }

    /**
     * Adds an item to the specified location's list of items
     *
     * @param view the view for adding an item
     */
    public void onAddItemPressed(View view){
        String shortDescription = shortField.getText().toString();
        String longDescription = longField.getText().toString();
        double value = Double.parseDouble(valueField.getText().toString());
        String comments = commentField.getText().toString();
        Log.d("MYAPP", "About to Add item");
        Item item = new Item(null, shortDescription, longDescription, value, category,
                comments, myLocation.getKey(), null);
        myLocation.setItemInList(item);
        String itemKey = mDatabase.push().getKey();
        mDatabase.child(itemKey).setValue(item);
        lDatabase.child(Integer.toString(myLocation.getKey())).setValue(myLocation);
        Intent i=new Intent(getApplicationContext(), DashboardActivityEmployee.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Location", myLocation);
        i.putExtras(bundle);
        startActivity(i);
    }
}
