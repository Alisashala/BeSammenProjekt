package com.example.besammenprojekt.Onboarding;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.besammenprojekt.MainActivity;
import com.example.besammenprojekt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//AppCompatActivity-klassen er en del af Android Support-biblioteket.
//extends: klassen udvider eller arver funktionaliteten fra en anden klasse.
//Det betyder, at LoginActivity vil arve alle de ikke-private egenskaber,
// metoder og andre medlemmer fra AppCompatActivity.

public class LoginActivity extends AppCompatActivity {
    //definerer private variabler af forskellige typer, der bruges til at håndtere brugerens loginfunktion

    private EditText editEmail;
    private EditText editPassword;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intentToMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentToMain);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialiserer og forbinder forskellige variabler med de tilsvarende elementer
        mAuth = FirebaseAuth.getInstance();
        editEmail = findViewById(R.id.et_email);
        editPassword = findViewById(R.id.et_password);
        loginBtn = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.signUpNow);
        textView.setOnClickListener(new View.OnClickListener() {

            //Når brugeren klikker på det View-objekt, der er knyttet til
            //Denne OnClickListener, vil der blive oprettet en Intent til at starte SignUpActivity,
            @Override
            public void onClick(View v) {
                Intent intentToSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intentToSignUp);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //sætter View som parameter og v returnere ingen værdi
                //Synligheden af en progressBar-komponent til at være synlig
                //ved at kalde setVisibility(View.VISIBLE) på progressBar-objektet.
                progressBar.setVisibility(View.VISIBLE);
                String email;
                String password;
                email = editEmail.getText().toString();
                password = editPassword.getText().toString(); //convert to string.

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent intentToMain = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intentToMain);
                                    finish();


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail: failed", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}