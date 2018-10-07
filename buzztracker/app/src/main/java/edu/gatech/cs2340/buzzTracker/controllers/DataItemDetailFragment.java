package edu.gatech.cs2340.buzzTracker.controllers;
import edu.gatech.cs2340.buzzTracker.controllers.DataItemDetailActivity;
import edu.gatech.cs2340.buzzTracker.controllers.DataItemListActivity;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.LocationsManager;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    /**
     * The dummy content this fragment is presenting.
     */
    private Location mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DataItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            int item_id = getArguments().getInt(ARG_ITEM_ID);
            Log.d("MYAPP", "Start details for: " + item_id);
            mItem = LocationsManager.getInstance().getData().get(item_id);

            Activity activity = this.getActivity();

        }
    }

    // Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dataitem_detail, container, false);
        Log.d("MYAPP", "Getting ready to set data");
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.key)).setText("Store Number: " + mItem.getKey());
            ((TextView) rootView.findViewById(R.id.name)).setText( mItem.getName());
            ((TextView) rootView.findViewById(R.id.addressLine1)).setText(mItem.getStreet());
            ((TextView) rootView.findViewById(R.id.addressLine2)).setText(mItem.getCity() + ", " +
                    mItem.getState() + " " + mItem.getZipcode());
            ((TextView) rootView.findViewById(R.id.type)).setText(mItem.getType());
            ((TextView) rootView.findViewById(R.id.phone)).setText(mItem.getPhone());
            ((TextView) rootView.findViewById(R.id.website)).setText(mItem.getWebsite());
        }

        return rootView;
    }
}
