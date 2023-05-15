package com.example.besammenprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView forgotPasswordTextView;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);
        forgotPasswordTextView = findViewById(R.id.tv_forgot_password);
        signUpButton = findViewById(R.id.btn_signup);

        // Set a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email and password entered by the user
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // TODO: Validate the email and password and authenticate the user

                // If authentication is successful, launch the front page of the app activity
                Intent intent = new Intent(LoginActivity.this, FrontPageActivity.class);
                startActivity(intent);

                // Finish the current activity so the user cannot go back to the login screen
                finish();
            }
        });

        // Set a click listener for the sign up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here you can write the code to handle the sign up button click event
                // For example, you can start a new activity to allow the user to sign up
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        // Set a click listener for the forgot password text view
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here you can write the code to handle the forgot password text view click event
                // For example, you can start a new activity to allow the user to reset their password
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}

