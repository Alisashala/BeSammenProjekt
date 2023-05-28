package com.example.besammenprojekt.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.besammenprojekt.MainActivity;
import com.example.besammenprojekt.R;

import java.util.ArrayList;
import java.util.List;

// Rajana

public class UserActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

    }
        // parameter view -->  det view der udløser begivenheden/eventet
        public void backButtonInUser(View view) {
           // Intent --> oprettelse af intent for at navigere tilbage til MainActivity
            // view.getContext() --> nuværende views kontekst bruges som det første argument til
            // Intent-konstruktøren
            Intent intentToMainActivity = new Intent(view.getContext(), MainActivity.class);

            // Intenten startes ved hjælp af metoden startActivity()
            startActivity(intentToMainActivity);

            // Afslutter den nuværende aktivitet og fjerner den fra aktivitetsstakken
            finish();
        }
    }
