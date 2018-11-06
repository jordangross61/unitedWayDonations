package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.User;

/**
 * Controller for the view where users can enter their primary Location
 */
public class RegisterLocation extends AppCompatActivity {
    private EditText locationField;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private User myEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_register);

        locationField = findViewById(R.id.location_field);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("MYAPP", "Made it to the registering location page");
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myEmployee = (User) bundle.getSerializable("User");
    }

    /**
     * Matches the location id that the user enters with a location that is registered on the app
     * and sets the user's primary location.
     * @param view the registration button
     */
    public void onRegistration(View view) {
        TextView errorMsg = findViewById(R.id.wrong_credentials_text);
        errorMsg.setText("");

        String location = locationField.getText().toString();
        DatabaseReference locDatabase = FirebaseDatabase.getInstance().getReference().child("locations").child(location);
        locDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("MYAPP", "Registering Location for an employee");
                Location loc = snapshot.getValue(Location.class);
                myEmployee.setLocation(loc);
                mDatabase.child("users").child(mAuth.getUid()).setValue(myEmployee);
                Intent i=new Intent(getApplicationContext(), DashboardActivityEmployee.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Location", loc);
                i.putExtras(bundle);
                startActivity(i);
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
                Log.d("MYAPP", "Retrieving specific location has an error");
            }
        });
    }
}
