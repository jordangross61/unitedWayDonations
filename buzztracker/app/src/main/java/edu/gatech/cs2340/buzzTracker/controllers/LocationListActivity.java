package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;

/**
 * An activity representing a list of Data Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DataItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class LocationListActivity extends AppCompatActivity {

    private DatabaseReference locationDatabase;
    private RecyclerView recyclerView;
    private SimpleItemRecyclerViewAdapter adapter;
    private List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        locationDatabase = FirebaseDatabase.getInstance().getReference().child("locations");

        recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;

        locations = new ArrayList<>();
        adapter = new SimpleItemRecyclerViewAdapter(locations);
        recyclerView.setAdapter(adapter);
        locationDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                getLocationDataToUpdateView(snapshot);
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
                Log.d("MYAPP", "Retrieving from database has error");
            }
        });
    }

    private void getLocationDataToUpdateView(DataSnapshot snapshot) {
        int index = 0;
        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
            Location loc = postSnapshot.getValue(Location.class);
            locations.add(loc);
            adapter.notifyItemInserted(index);
            index++;
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * Represents a recycler view adapter for the list of locations
     */
    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Location> mValues;

        /**
         * constructor for the SimpleItemRecyclerViewAdapter
         * @param items the list of locations that will be displayed
         */
        public SimpleItemRecyclerViewAdapter(List<Location> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mContentView.setText(mValues.get(position).getName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, LocationDetailActivity.class);
                    Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getKey());
                    intent.putExtra(LocationDetailFragment.ARG_ITEM_ID, holder.mItem.getKey() - 1);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;
            public Location mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
