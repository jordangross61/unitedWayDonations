package edu.gatech.cs2340.buzzTracker.controllers;
//import edu.gatech.cs2340.buzzTracker.controllers.DataItemDetailActivity;
import edu.gatech.cs2340.buzzTracker.controllers.DataItemListActivity;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A fragment representing a single DataItem detail screen.
 * This fragment is either contained in a {@link DataItemListActivity}
 * in two-pane mode (on tablets) or a {@link DataItemDetailActivity}
 * on handsets.
 */
public class DataItemDetailFragment extends AppCompatActivity {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    private DatabaseReference itemDatabase;
    /**
     * The dummy content this fragment is presenting.
     */
    private Item mItem;

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
        mItem  = (Item) bundle.getSerializable("Item");

        final TextView helloTextView = (TextView) findViewById(R.id.text_view_id);
        helloTextView.setText(mItem.toString());
    }



   /*     if (savedInstanceState != null) {
            Item temp = (Item) savedInstanceState.getSerializable(ARG_ITEM_ID);
            mItem = temp == null ? mItem :temp;
        }


        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            Log.d("MYAPP", "fragment had item object");
            ((TextView) rootView.findViewById(R.id.time)).setText("Store Number: " + mItem.getTime());
            ((TextView) rootView.findViewById(R.id.shortDescription)).setText(mItem.getShortDescription());
            ((TextView) rootView.findViewById(R.id.longDescription)).setText(mItem.getLongDescription());
            ((TextView) rootView.findViewById(R.id.category)).setText(mItem.getCategory().toString());
            ((TextView) rootView.findViewById(R.id.comments)).setText(mItem.getComments());
        }

        return rootView;
    }*/
}
