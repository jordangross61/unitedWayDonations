package edu.gatech.cs2340.buzzTracker.controllers;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

/**
 * A fragment representing a single DataItem detail screen.
 * This fragment is contained in a {@link DataItemListActivity}
 * or a {@link DataItemDetailFragment}
 */
public class DataItemDetailFragment extends AppCompatActivity {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    // private DatabaseReference itemDatabase;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataitem_detail);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        /*
      The dummy content this fragment is presenting.
     */
        Item mItem = (Item) Objects.requireNonNull(bundle).getSerializable("Item");

        final TextView categoryTextView = findViewById(R.id.text_view_category);
        final TextView shortDescriptionTextView = findViewById(R.id.text_view_short);
        final TextView longDescriptionTextView = findViewById(R.id.text_view_long);
        final TextView timeTextView = findViewById(R.id.text_view_time);
        final TextView sizeTextView = findViewById(R.id.text_view_size);
        final TextView valueTextView = findViewById(R.id.text_view_value);
        final TextView commentTextView = findViewById(R.id.text_view_comments);

        String category = "Category : " + Objects.requireNonNull(mItem).getCategory().toString();
        String shortDes = "Short Description : " + mItem.getShortDescription();
        String longDes = "Long Description : " + mItem.getLongDescription();
        String time = "Time Entered: " + mItem.getTime();
        String size = "Size: " + mItem.getSize();
        String value = "Value: $" + mItem.getValue();
        String comments = "Additional Comments: " + mItem.getComments();

        categoryTextView.setText(category);
        shortDescriptionTextView.setText(shortDes);
        longDescriptionTextView.setText(longDes);
        timeTextView.setText(time);
        sizeTextView.setText(size);
        valueTextView.setText(value);
        commentTextView.setText(comments);
    }
}