package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;

/**
 * Controller for displaying the search results
 */
public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<Item> items = (ArrayList<Item>) Objects.requireNonNull(bundle).getSerializable("QueriedItems");

        if (items != null) {
            ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_search_results_item, items);

            ListView listView = findViewById(R.id.item_list);
            listView.setAdapter(adapter);
        }
    }
}
