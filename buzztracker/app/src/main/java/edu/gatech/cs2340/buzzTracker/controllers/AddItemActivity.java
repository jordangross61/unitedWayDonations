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
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.User;

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
    private FirebaseAuth mAuth;
    private ItemType category;
    private String itemKey;

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
        category = (ItemType) bundle.getSerializable("Category");
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("items");
        itemKey = mDatabase.push().getKey();
        Log.d("MYAPP", "Into the Add Item Activity Page");
    }

    public void onAddItemPressed(View view){
        String shortDescription = shortField.getText().toString();
        String longDescription = longField.getText().toString();
        double value = Double.parseDouble(valueField.getText().toString());
        String comments = commentField.getText().toString();
        Log.d("MYAPP", "About to Add item");
        Item item = new Item(null, myLocation, shortDescription, longDescription, value, category, comments);
        mDatabase.child(itemKey).setValue(item);
        Log.d("MYAPP", "Successfully added item");
        Intent i=new Intent(getApplicationContext(), DashboardActivityEmployee.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Location", myLocation);
        i.putExtras(bundle);
        startActivity(i);
    }

}
