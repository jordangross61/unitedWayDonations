package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.UserRights;

/**
 * Controller for the add category view of application
 */
public class AddCategoryActivity extends AppCompatActivity {


    private Spinner categorySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        categorySpinner = findViewById(R.id.category_spinner);

        ArrayAdapter<UserRights> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    public void onAddCategoryPressed(View view){
        ItemType selected = (ItemType)categorySpinner.getSelectedItem();
        // if item being added would need a size
        if (selected.equals(ItemType.CLOTHING)) {
            startActivity(new Intent(this, AddSizeItemActivity.class));
        } else {
            startActivity(new Intent(this, AddItemActivity.class));
        }
    }
}
