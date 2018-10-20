package edu.gatech.cs2340.buzzTracker.controllers;

import edu.gatech.cs2340.buzzTracker.R;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Data Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DataItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class DataItemListActivity extends AppCompatActivity {

    private DatabaseReference itemDatabase;
    private RecyclerView recyclerView;
    private SimpleItemRecyclerViewAdapter adapter;
    private List<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataitem_list);
        itemDatabase = FirebaseDatabase.getInstance().getReference().child("items");

        recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        items = (ArrayList<Item>) bundle.getSerializable("ItemList");
        adapter = new SimpleItemRecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        public SimpleItemRecyclerViewAdapter(List<Item> items) {
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
            holder.mContentView.setText(mValues.get(position).getCategory().toString() + ": " +
                    mValues.get(position).getShortDescription());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DataItemDetailActivity.class);
                    Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getId());
                    intent.putExtra(DataItemDetailFragment.ARG_ITEM_ID, holder.mItem.getId() - 1);
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
