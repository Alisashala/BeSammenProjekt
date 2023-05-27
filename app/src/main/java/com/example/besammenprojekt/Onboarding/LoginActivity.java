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
//extends --> klassen udvider eller arver funktionaliteten fra en anden klasse.
//Det betyder, at LoginActivity vil arve alle de ikke-private egenskaber,
// metoder og andre medlemmer fra AppCompatActivity.

public class LoginActivity extends AppCompatActivity {

    // Definerer private variabler af forskellige typer, der bruges til at håndtere
    // brugerens loginfunktion.
    private EditText editEmail;
    private EditText editPassword;
    private Button loginBtn;
    private FirebaseAuth mAuth; // Instans af Firebase-autentificering.
    private ProgressBar progressBar; //
    private TextView textView; //

    @Override
    public void onStart() {
        super.onStart();

        // Tjekker om brugeren er logget ind (ikke-null) og opdaterer brugergrænsefladen (UI)
        // Der oprettes en FirebaseUser-variabel for at få adgang til den aktuelle bruger.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Hvis brugeren er logget ind (currentUser er ikke-null), oprettes en Intent for at
        // navigere til MainActivity.
        if(currentUser != null){
            Intent intentToMain = new Intent(getApplicationContext(), MainActivity.class);

            // Intenten startes ved hjælp af metoden startActivity()
            startActivity(intentToMain);

            // Afslutter den nuværende aktivitet og fjern den fra aktivitetsstakken
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

// textView onClick

        // Sætter en OnClickListener på textView-objektet.
        textView.setOnClickListener(new View.OnClickListener() {

            // Når brugeren klikker på dette View, oprettesintent  for at navigere fra LoginActivity
            // til MainActivity
            @Override
            public void onClick(View v) {
                Intent intentToSignUp = new Intent(LoginActivity.this, SignUpActivity.class);

                // Intenten startes ved hjælp af metoden startActivity()
                startActivity(intentToSignUp);

                // Afslutter den nuværende aktivitet og fjerner den fra aktivitetsstakken
                finish();
            }
        });

// login button onClick
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //sætter View som parameter og v returnere ingen værdi

                //Synligheden af en progressBar-komponent til at være synlig
                //ved at kalde setVisibility(View.VISIBLE) på progressBar-objektet.
                progressBar.setVisibility(View.VISIBLE);

                // - Email og password hentes fra editEmail- og editPassword-felterne
                // og konverteres til strenge.
                String email;
                String password;
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();

                // - Hvis email-feltet er tomt, vises en kort besked om at indtaste en email
                // ved hjælp af Toast.
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                // - Hvis password-feltet er tomt, vises en kort besked om at indtaste et password
                // ved hjælp af Toast.
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                // - Hvis både email og password er udfyldt, forsøges der at logge ind ved hjælp af
                // FirebaseAuths signInWithEmailAndPassword-metode.
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // - Når log ind-processen er afsluttet, skjules
                                // ProgressBar-komponenten ved at indstille synligheden til View.GONE.
                                progressBar.setVisibility(View.GONE);
                                // - Hvis log ind er vellykket, vises en kort besked om, at log ind
                                // er lykkedes ved hjælp af Toast.
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();

                                    Intent intentToMain = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intentToMain);
                                    finish();


                                } else {

                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}