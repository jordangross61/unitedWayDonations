package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Objects;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.Location;

/**
 * Controller for the add category view of application
 */
public class AddCategoryActivity extends AppCompatActivity {


    private Spinner categorySpinner;
    private Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MYAPP", "Made it into add category activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        categorySpinner = findViewById(R.id.category_spinner);

        ArrayAdapter<ItemType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myLocation = (Location) Objects.requireNonNull(bundle).getSerializable("Location");
    }

    /**
     * Determines if the new item has a size or not, then starts the corresponding activity
     *
     * @param view the view for selecting the item's category
     */
    public void onAddCategoryPressed(View view) {
        ItemType selected = (ItemType) categorySpinner.getSelectedItem();
        // if item being added would need a size
        if (selected.equals(ItemType.CLOTHING)) {
            Intent i = new Intent(getApplicationContext(), AddSizeItemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Location", myLocation);
            bundle.putSerializable("Category", selected.toString());
            Log.d("MYAPP", "Created bundle - about to go to individual add size item page now");
            i.putExtras(bundle);
            startActivity(i);
        } else {
            Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Location", myLocation);
            bundle.putSerializable("Category", selected.toString());
            Log.d("MYAPP", "Created bundle - about to go to individual add item page now");
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}
