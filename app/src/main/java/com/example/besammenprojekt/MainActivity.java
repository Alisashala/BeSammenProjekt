package com.example.besammenprojekt;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.besammenprojekt.Details.RulesAndInformation;
import com.example.besammenprojekt.Onboarding.LoginActivity;
import com.example.besammenprojekt.User.AgeActivity;
import com.example.besammenprojekt.User.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

// Rajana

    // Firebase-authentifikation
    private FirebaseAuth auth;  // FirebaseAuth-instans
    private FirebaseUser user; // Bruger i Firebase

    // Brugergrænseflade-elementer / interface components
    private TextView textView;

    private Button logOut;
    private ImageView groupChat;
    private ImageView privateChat;

// Rajana

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Henter intenten, der blev sendt til denne aktivitet
        Intent intentToMain = getIntent();

        // Henter den medfølgende strengværdi med nøgle "key" fra intenten
        String message = intentToMain.getStringExtra("key");

        // Finder og refererer til TextView-komponenten til visning af brugernavn
        TextView username = findViewById(R.id.userInfo);

        // Indstiller den modtagne besked som teksten i TextView
        username.setText(message);


        // Initialiserer Firebase-autentificeringsobjektet
        auth = FirebaseAuth.getInstance();
        // Finder og refererer til TextView-komponenten til userInfo
        textView = findViewById(R.id.userInfo);
        // Henter den aktuelt logget ind bruger fra Firebase
        user = auth.getCurrentUser();

        // Hvis brugeren ikke er logget ind, oprettes en Intent til at starte LoginActivity.
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Hvis brugeren er logget ind, indstilles brugernavnet som teksten i TextView.
            username.setText(message);
        }

// Rajana

        // Finder view-elementet med id'et privateChat i layoutfilen og tilordner det til variablen privateChat
        privateChat = findViewById(R.id.privateChat);
        // Sætter en OnClickListener til privateChat og definerer dens onClick-metode
        privateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opretter en Intent til at starte UserActivity
                Intent intentToPrivateChat = new Intent(view.getContext(), UserActivity.class);
                startActivity(intentToPrivateChat);
                finish();
            }
        });

// Alisa

        // Finder view-elementet med id'et groupChat i layoutfilen og tilordner det til variablen groupChat.
        groupChat = findViewById(R.id.groupChat);
        // Sætter en OnClickListener til groupChat og definerer dens onClick-metode.
        groupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToGroupChat = new Intent(view.getContext(), AgeActivity.class);
                startActivity(intentToGroupChat);
                finish();
            }
        });

// Rajana

        // Finder view-elementet med id'et signOutButton i layoutfilen og tilordner det til
        // variablen signOutButton
        ImageView signOutButton = findViewById(R.id.signOutButton);

        // Sætter en OnClickListener til signOutButton og definerer dens onClick-metode
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Denne metode bliver udført, når signOutButton bliver trykket på

                // Logger brugeren ud ved at kalde signOut() på FirebaseAuth-objektet
                FirebaseAuth.getInstance().signOut();

                // Opretter en Intent til at starte LoginActivity
                Intent intentToLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentToLoginActivity);
                finish();

            }
        });

// Rajana

        // Finder view-elementet med id'et rulesAndInformation i layoutfilen og tilordner det til variablen rulesAndInformation
        ImageView rulesAndInformation = findViewById(R.id.rulesAndInformation);
        // Sætter en OnClickListener til rulesAndInformation og definerer dens onClick-metode
        rulesAndInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Denne metode bliver udført, når rulesAndInformation bliver trykket på

                // Opretter en Intent til at navigere til RulesAndInformation-aktiviteten
                Intent intentToRulesAndInformation = new Intent(getApplicationContext(), RulesAndInformation.class);
                startActivity(intentToRulesAndInformation);
                finish();
            }
        });

    }
}

