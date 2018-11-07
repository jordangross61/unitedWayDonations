package edu.gatech.cs2340.buzzTracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.User;
import edu.gatech.cs2340.buzzTracker.model.UserRights;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Controller for the main dashboard view of application
 */
public class RegistrationActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private Spinner rightsSpinner;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        nameField = findViewById(R.id.name_field);
        rightsSpinner = findViewById(R.id.access_spinner);

        if (UserRights.values().length != 0) {
            ArrayAdapter<UserRights> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserRights.values());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            rightsSpinner.setAdapter(adapter);
        }

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Starts the login activity if the user decides they don't need to register
     * @param view the login button
     */
    public void onLoginOptPressed(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**
     * Creates a new user account with the provided information and adds the user to Firebase
     * @param view the registration button
     */
    public void onRegistration(View view) {
        TextView errorMsg = findViewById(R.id.wrong_credentials_text);
        errorMsg.setText("");

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        final UserRights myRights = (UserRights)rightsSpinner.getSelectedItem();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(nameField.getText().toString(), emailField.getText().toString(), passwordField.getText().toString(), myRights);
                            if (myRights.equals(UserRights.USER)) {
                                addToDatabase(user, mAuth.getUid());
                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                            } else if ((myRights.equals(UserRights.EMPLOYEE))) {
                                Log.d ("MYAPP", "Noticed that we are registering employee");
                                Intent i=new Intent(getApplicationContext(), RegisterLocation.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("User", user);
                                i.putExtras(bundle);
                                startActivity(i);
                            }
                        } else {
                            TextView errorMsg = findViewById(R.id.wrong_credentials_text);
                            String error = "Error";
                            errorMsg.setText(error);
                        }
                    }
                });
    }

    private void addToDatabase(User user, String userid) {
        mDatabase.child("users").child(userid).setValue(user);
    }
}
