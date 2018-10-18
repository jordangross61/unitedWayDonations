package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.User;
import edu.gatech.cs2340.buzzTracker.model.UserRights;

public class RegisterLocation extends AppCompatActivity {
    private EditText locationField;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private User myEmployee;
    private DatabaseReference locDatabase;

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

    public void onRegistration(View view) {
        TextView errorMsg = findViewById(R.id.wrong_credentials_text);
        errorMsg.setText("");

        String location = locationField.getText().toString();
        locDatabase = FirebaseDatabase.getInstance().getReference().child("locations").child(location);
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
