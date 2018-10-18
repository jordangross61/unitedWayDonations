package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.Size;
import edu.gatech.cs2340.buzzTracker.model.UserRights;

/**
 * Controller for the add item view of application
 */
public class AddSizeItemActivity extends AppCompatActivity {

    private EditText shortField;
    private EditText longField;
    private EditText valueField;
    private EditText commentField;
    private Spinner sizeSpinner;
    private Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_with_size);

        shortField = findViewById(R.id.short_field);
        longField = findViewById(R.id.long_field);
        valueField = findViewById(R.id.value_field);
        commentField = findViewById(R.id.comments_field);
        sizeSpinner = findViewById(R.id.size_spinner);

        ArrayAdapter<UserRights> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Size.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myLocation = (Location) bundle.getSerializable("Location");
    }

    public void onAddItemPressed(View view){
        startActivity(new Intent(this, DashboardActivityEmployee.class));    }

}
