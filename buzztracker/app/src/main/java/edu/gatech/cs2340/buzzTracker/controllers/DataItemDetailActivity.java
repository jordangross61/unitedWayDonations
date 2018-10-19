package edu.gatech.cs2340.buzzTracker.controllers;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

/**
 * An activity representing a single DataItem detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link DataItemListActivity}.
 */
public class DataItemDetailActivity extends AppCompatActivity {

    private DatabaseReference itemDatabase;
    private RecyclerView recyclerView;
    private DataItemDetailActivity.SimpleItemRecyclerViewAdapter adapter;
    private List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        itemDatabase = FirebaseDatabase.getInstance().getReference().child("items");

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        itemList = (List<Item>) bundle.getSerializable("ItemList");

        recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;

        adapter = new DataItemDetailActivity.SimpleItemRecyclerViewAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<DataItemDetailActivity.SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        public SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        public DataItemDetailActivity.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_list_content, parent, false);
            return new DataItemDetailActivity.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final DataItemDetailActivity.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            //holder.mContentView.setText(mValues.get(position).getName());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = holder.mItem.getItemKey();
                    itemDatabase.child(key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            Log.d("MYAPP", "Grabbing specific item");
                            Item mItem = snapshot.getValue(Item.class);
                            Intent i=new Intent(getApplicationContext(), DataItemDetailFragment.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Item", mItem);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                        @Override
                        public void onCancelled(DatabaseError DatabaseError) {
                            Log.d("MYAPP", "Retrieving specific item has error");
                        }
                    });


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
            public Item mItem;

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
