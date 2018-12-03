package edu.gatech.cs2340.buzzTracker.controllers;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Objects;

import edu.gatech.cs2340.buzzTracker.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.cs2340.buzzTracker.R;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.User;
import edu.gatech.cs2340.buzzTracker.model.UserRights;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class FacebookActivity extends AppCompatActivity {
    CallbackManager callbackManager = CallbackManager.Factory.create();
    private FirebaseAuth mAuth;
    private static final String TAG = "Facebook";
    private DatabaseReference locDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = findViewById(R.id.login_button_fb);
        loginButton.setReadPermissions("email", "public_profile");


        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
            @Override
                public void onSuccess(LoginResult loginResult) {
                Log.d("SUCCESSS", "test");
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                }

            @Override
                public void onCancel() {
                Log.d("FAIL", "test");
                }

            @Override
                public void onError(FacebookException exception) {
                    Log.d("ERROR", "test");
                }
            });
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }
/*
    private void showScreenBas edOnRights(String userid) {
        DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userid);
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("MYAPP", "Goes into data snapshot");
                User user = snapshot.getValue(User.class);
                    Log.d("MYAPP", "Gets into proper user rights");
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError DatabaseError) {
                Log.d("MYAPP", "Retrieving from database has error");
            }
        });
    }
    */

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
       // if (user != null) {
           // String userid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
       // } else {
         //   TextView errorMsg = findViewById(R.id.wrong_credentials_text);
       //     String error = "Error";
       //     errorMsg.setText(error);
       // }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithCredential:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithCredential:failure", task.getException());
                Toast.makeText(FacebookActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                updateUI(null);
            }

                // ...
        }
    });
    }
}
