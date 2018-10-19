package edu.gatech.cs2340.buzzTracker.controllers;
import edu.gatech.cs2340.buzzTracker.controllers.DataItemDetailActivity;
import edu.gatech.cs2340.buzzTracker.controllers.DataItemListActivity;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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
public class DataItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private DatabaseReference itemDatabase;
    /**
     * The dummy content this fragment is presenting.
     */
    private Item mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DataItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Item temp = (Item) savedInstanceState.getSerializable(ARG_ITEM_ID);
            mItem = temp == null ? mItem :temp;
        }

    }

    // Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dataitem_detail, container, false);
        if (savedInstanceState != null) {
            Item temp = (Item) savedInstanceState.getSerializable(ARG_ITEM_ID);
            mItem = temp == null ? mItem :temp;
        }

        if (container == null) {
            Log.d("MYAPP", "fragment not attached to view");
        }

        Log.d("MYAPP", "Getting ready to set data");

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
    }


    public void updateItem(Item mItem) {
        this.mItem = mItem;
        // Show the dummy content as text in a TextView.
        /*Log.d("MYAPP", "fragment init complete");
        Log.d("MYAPP", "updating item");
        View rootView = getView();
        if (mItem != null) {
            Log.d("MYAPP", "item non null");
            ((TextView) rootView.findViewById(R.id.key)).setText("Store Number: " + mItem.getKey());
            ((TextView) rootView.findViewById(R.id.name)).setText( mItem.getName());
            ((TextView) rootView.findViewById(R.id.addressLine1)).setText(mItem.getStreet());
            ((TextView) rootView.findViewById(R.id.addressLine2)).setText(mItem.getCity() + ", " +
                    mItem.getState() + " " + mItem.getZipcode());
            ((TextView) rootView.findViewById(R.id.type)).setText(mItem.getType());
            ((TextView) rootView.findViewById(R.id.phone)).setText(mItem.getPhone());
            ((TextView) rootView.findViewById(R.id.website)).setText(mItem.getWebsite());
        }*/
    }
}
