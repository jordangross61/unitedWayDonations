package edu.gatech.cs2340.buzzTracker.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.User;
import edu.gatech.cs2340.buzzTracker.model.UserRights;
//import edu.gatech.cs2340.buzzTracker.model.LoginServiceFacade;
//import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//import com.google.firebase.quickstart.auth.R;
/**
 * This is the Controller for the Login View
 */
public class LoginActivity extends AppCompatActivity {

    //persistence work for firebase
    private FirebaseAuth mAuth;
    private DatabaseReference userDatabase;
    private DatabaseReference locDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Button handler for pressing the login button
     *
     * @param view reference to actual button pressed
     */
    public void onLoginPressed(View view) {
        EditText emailField = findViewById(R.id.email_field);
        EditText passwordField = findViewById(R.id.password_field);

        TextView errorMsg = findViewById(R.id.wrong_credentials_text);

        errorMsg.setText("");

        mAuth.signInWithEmailAndPassword(emailField.getText().toString(), passwordField.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    showScreenBasedOnRights(userid);
                                    //startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                } else {
                                    TextView errorMsg = findViewById(R.id.wrong_credentials_text);
                                    errorMsg.setText("Error");
                                }
                            }
                        });

    }

    public void onRegistrationOptPressed(View view){
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void showScreenBasedOnRights(String userid) {
        userDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userid);
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("MYAPP", "Goes into data snapshot");
                User user = snapshot.getValue(User.class);

                if (user.getRights().equals(UserRights.USER)) {
                    Log.d("MYAPP", "Gets into proper user rights");
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                } else if (user.getRights().equals(UserRights.EMPLOYEE)) {
                    String locKey = Integer.toString(user.getLocation().getKey());
                    locDatabase = FirebaseDatabase.getInstance().getReference().child("locations").child(locKey);
                    locDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            Log.d("MYAPP", "Grabs the updated version of the location");
                            Location loc = snapshot.getValue(Location.class);
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
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
                Log.d("MYAPP", "Retrieving from database has error");
            }
        });
    }
}
