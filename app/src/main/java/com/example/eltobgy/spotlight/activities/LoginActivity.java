package com.example.eltobgy.spotlight.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eltobgy.spotlight.R;
import com.example.eltobgy.spotlight.models.User;
import com.example.eltobgy.spotlight.utlis.Helper;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.hawk.Hawk;

import java.util.Arrays;


/**
 * Created by Eltobgy on 31-May-18.
 */

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 1;
    public static DatabaseReference mDatabaseReference;
    public static FirebaseDatabase mDatabase;
    public static User currentUser;
    public static DatabaseReference userDatabaseReference;
    String email;
    EditText emailEditText;
    Button loginButton;
    FirebaseUser firebaseUser;
    ValueEventListener valueEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        Hawk.init(this).build();
        //setContentView(R.layout.activity_profile);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing you in ...");
        progressDialog.show();


        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mDatabase.getReference().child("users");

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                Log.e("First  ", String.valueOf(firebaseUser));
                if (firebaseUser != null) {
                    // User is signed in
                    userDatabaseReference = mDatabaseReference.child(firebaseUser.getUid());
                    valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            currentUser = dataSnapshot.getValue(User.class);
                            userInfoAction();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Helper.showToast(LoginActivity.this, "cancelled");
                            Helper.showLog(LOG_TAG, databaseError.toString());
                        }
                    };
                    userDatabaseReference.addValueEventListener(valueEventListener);

                } else {
                    // User is signed out
                    authenticateUser();
                }
            }
        };
    }

    private void authenticateUser() {

        //  List<AuthUI.IdpConfig> providers = Arrays.asList(
        //       new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());

        //TODO ADD  CONTINUE AS A GUEST
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build()))
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.drawable.spotlight2)
                        .build(),
                RC_SIGN_IN);
    }


    private void userInfoAction() {
        progressDialog.dismiss();
        if (currentUser != null) {

            Helper.showLog(LOG_TAG, "USER B EXISTS" + String.valueOf(currentUser));
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Helper.showToast(LoginActivity.this, "user info");
            insertNewUser();
            Helper.showLog(LOG_TAG, "current user b null");
        }
    }


    private void insertNewUser() {

        Log.e("inserting new user", "AUTHACTIVITY");
        currentUser = new User();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUser.setId(firebaseUser.getUid());
        currentUser.setUsername(firebaseUser.getDisplayName());
        currentUser.setEmail(firebaseUser.getEmail());


        mDatabaseReference.child(firebaseUser.getUid()).setValue(currentUser);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

/**
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.activity_login);
 * Hawk.init(this).build();
 * <p>
 * declareViews();
 * <p>
 * loginButton.setOnClickListener(new View.OnClickListener() {
 * @Override public void onClick(View v) {
 * email=emailEditText.getText().toString();
 * <p>
 * if(isEmailValid(email)){
 * Hawk.put(Constants.mEmail_Key,email);
 * Helper.showLog(LOG_TAG,"Registered "+email);
 * startActivity(new Intent(LoginActivity.this,MainActivity.class));
 * }else{
 * emailEditText.setError("Wrong email format");
 * }
 * }
 * });
 * }
 * <p>
 * private void declareViews() {
 * emailEditText = findViewById(R.id.et_email);
 * <p>
 * <p>
 * loginButton = findViewById(R.id.btn_join);
 * }
 * <p>
 * boolean isEmailValid(CharSequence email) {
 * return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
 * }
 * }
 **/
