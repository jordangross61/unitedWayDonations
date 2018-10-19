package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private DatabaseReference lDatabase;
    private FirebaseAuth mAuth;
    private ItemType category;



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
        mDatabase = FirebaseDatabase.getInstance().getReference().child("items");
        lDatabase = FirebaseDatabase.getInstance().getReference().child("locations");
        Log.d("MYAPP", "Into the Add Item Activity Page");
    }

    public void onAddItemPressed(View view){
        String shortDescription = shortField.getText().toString();
        String longDescription = longField.getText().toString();
        double value = Double.parseDouble(valueField.getText().toString());
        String comments = commentField.getText().toString();
        Log.d("MYAPP", "About to Add item");
        String itemKey = mDatabase.push().getKey();
        Item item = new Item(null, shortDescription, longDescription, value, category, comments, itemKey);
        myLocation.setItemInList(item);
        mDatabase.child(itemKey).setValue(item);
        lDatabase.child(Integer.toString(myLocation.getKey())).setValue(myLocation);
        Intent i=new Intent(getApplicationContext(), DashboardActivityEmployee.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Location", myLocation);
        i.putExtras(bundle);
        startActivity(i);
    }
}
