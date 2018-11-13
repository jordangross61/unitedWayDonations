package edu.gatech.cs2340.buzzTracker.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;

/**
 * A fragment representing a single DataItem detail screen.
 * This fragment is either contained in a {@link DataItemListActivity}
 * in two-pane mode (on tablets) or a {@link DataItemDetailFragment}
 * on handsets.
 */
public class LocationDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private static final String ARG_LOCATION_ID = "loc_id";
    // private DatabaseReference locationDatabase;

    /**
     * The dummy content this fragment is presenting.
     */
    private Location mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Location temp = (Location) savedInstanceState.getSerializable(ARG_LOCATION_ID);
            mItem = (temp == null ? mItem :temp);
        }

    }

    // Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);
        if (savedInstanceState != null) {
            Location temp = (Location) savedInstanceState.getSerializable(ARG_LOCATION_ID);
            mItem = (temp == null ? mItem :temp);
        }

        if (container == null) {
            Log.d("MYAPP", "fragment not attached to view");
        }

        Log.d("MYAPP", "Getting ready to set data");
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            Log.d("MYAPP", "fragment had location object");

            String storeNum = "Store Number: " + mItem.getKey();
            String address = mItem.getCity() + ", " + mItem.getState() + " " + mItem.getZipcode();

            ((TextView) rootView.findViewById(R.id.key)).setText(storeNum);
            ((TextView) rootView.findViewById(R.id.name)).setText( mItem.getName());
            ((TextView) rootView.findViewById(R.id.addressLine1)).setText(mItem.getStreet());
            ((TextView) rootView.findViewById(R.id.addressLine2)).setText(address);
            ((TextView) rootView.findViewById(R.id.type)).setText(mItem.getType());
            ((TextView) rootView.findViewById(R.id.phone)).setText(mItem.getPhone());
            ((TextView) rootView.findViewById(R.id.website)).setText(mItem.getWebsite());
        }

        return rootView;
    }

    /**
     * Updates the location
     *
     * @param mItem item in location
     */
    public void updateLocation(Location mItem) {
        this.mItem = mItem;
    }
}
