package edu.gatech.cs2340.buzzTracker.controllers;

import edu.gatech.cs2340.buzzTracker.R;

import edu.gatech.cs2340.buzzTracker.model.Item;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An activity representing a list of Data Items. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DataItemDetailFragment} representing
 * item details.
 */
public class DataItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataitem_list);
        DatabaseReference itemDatabase = FirebaseDatabase.getInstance().getReference().child("items");

        RecyclerView recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        List<Item> items = (ArrayList<Item>) Objects.requireNonNull(bundle).getSerializable("ItemList");
        if (items != null) {
            SimpleItemRecyclerViewAdapter adapter = new SimpleItemRecyclerViewAdapter(items);
            recyclerView.setAdapter(adapter);
        }
    }

    /**
     * Represents a view for the list of items
     */
    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        /**
         * constructor that sets the field mValues with the list of items
         * 
         * @param items the list of items
         */
        SimpleItemRecyclerViewAdapter(List<Item> items) {
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
            String contentText = mValues.get(position).getCategory().toString() + ": " +
                    mValues.get(position).getShortDescription();
            holder.mItem = mValues.get(position);
            holder.mContentView.setText(contentText);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), DataItemDetailFragment.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Item", holder.mItem);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * Class that holds a view and an item
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mContentView;
            Item mItem;

            /**
             * constructor for the ViewHolder class
             * @param view the view that this ViewHolder is holding
             */
            ViewHolder(View view) {
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
