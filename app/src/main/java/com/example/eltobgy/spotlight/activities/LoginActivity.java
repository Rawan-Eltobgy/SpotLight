package com.example.eltobgy.spotlight.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eltobgy.spotlight.MainActivity;
import com.example.eltobgy.spotlight.R;


/**
 * Created by Eltobgy on 31-May-18.
 */

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    String email;
    EditText emailEditText;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        declareViews();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=emailEditText.getText().toString();

                if(isEmailValid(email)){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    emailEditText.setError("Wrong email format");
                }
            }
        });
    }

    private void declareViews() {
         emailEditText = findViewById(R.id.et_email);


         loginButton = findViewById(R.id.btn_join);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
