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

import java.io.Serializable;
import java.util.List;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;

/**
 * Controller for the main dashboard view of application
 */
public class LocationsActivity extends AppCompatActivity implements Serializable {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
    }

    public void onRegistrationOptPressed(View view){
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void onLoginOptPressed(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Location> mValues;

        public SimpleItemRecyclerViewAdapter(List<Location> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dataitem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mContentView.setText(mValues.get(position).getName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putInt(DataItemDetailFragment.ARG_ITEM_ID, holder.mItem.getKey());
                        DataItemDetailFragment fragment = new DataItemDetailFragment();
                        fragment.setArguments(arguments);
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.dataitem_detail_container, fragment)
//                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DataItemDetailActivity.class);
                        Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getKey());
                        intent.putExtra(DataItemDetailFragment.ARG_ITEM_ID, holder.mItem.getKey());

                        context.startActivity(intent);
                    }
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
