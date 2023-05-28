package com.example.besammenprojekt.Onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignUpActivity extends AppCompatActivity {

// Nadine

    // Definerer private variabler af forskellige typer, der bruges til at håndtere brugerens loginfunktion.
    private EditText editEmail;
    private EditText editPassword;
    private Button signUpBtn;
    public EditText userName;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView textView;

// Alisa

    @Override
    public void onStart() {
        super.onStart();

        // Tjekker om brugeren er logget ind (ikke-null) og opdaterer brugergrænsefladen (UI)
        // Først hentes den aktuelle bruger fra Firebase Authentication ved hjælp af mAuth.getCurrentUser() metoden.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Hvis brugeren er logget ind, startes MainActivity og loginskærmen afsluttes.
        if(currentUser != null){
            Intent intentToMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentToMain);
            finish();
        }
    }

// Alisa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialiserer Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Identificerer 'view'-komponenterne i layoutfilen ved hjælp af deres unikke id'er og tilordner dem til de tilsvarende variabler.
        editEmail = findViewById(R.id.et_email);
        editPassword = findViewById(R.id.et_password);
        signUpBtn = findViewById(R.id.btn_signup);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);

// Alisa

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            // Når brugeren klikker på dette View, oprettes intent  for at navigere fra SignUpActivity til LoginActivity
            public void onClick(View v) {
                Intent intentToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentToLogin);
                finish();
            }
        });

// Rajana

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Når signUpBtn bliver klikket, gøres progressBar-komponenten synlig.
                progressBar.setVisibility(View.VISIBLE);

                // Email og password hentes fra editEmail- og editPassword-felterne og konverteres til strenge.
                String email;
                String password;
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();

                // Hvis email-feltet er tomt, vises en kort besked om at indtaste en email ved hjælp af Toast.
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Hvis password-feltet er tomt, vises en kort besked om at indtaste et password ved hjælp af Toast.
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Opretter en ny brugerkonto i Firebase Authentication ved hjælp af den angivne email og adgangskode.

// Alisa
                mAuth.createUserWithEmailAndPassword(email, password)

                        // Tilføjer en onCompleteListener til at lytte efter afslutningen af oprettelsesprocessen.
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Når oprettelsen er fuldført, skjules progressBar-komponenten.
                                progressBar.setVisibility(View.GONE);

                                // Hvis oprettelsen er succesfuld, vises en kort besked om, at kontoen er oprettet.
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Account created",
                                            Toast.LENGTH_SHORT).show();

                                    // Indsamler brugernavn fra det tilhørende tekstfelt.
                                    userName = findViewById(R.id.et_userName);
                                    String message = userName.getText().toString();

                                    // Opretter en Intent til at navigere fra SignupActivity til MainActivity.
                                    Intent intentToMain = new Intent(SignUpActivity.this, MainActivity.class);
                                    intentToMain.putExtra("key", message);
                                    startActivity(intentToMain);

                                } else {
                                    // Hvis oprettelsen fejler, vises en kort besked om, at godkendelsen mislykkedes.
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


    }

}