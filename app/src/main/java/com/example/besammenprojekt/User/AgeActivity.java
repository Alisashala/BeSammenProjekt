package com.example.besammenprojekt.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.besammenprojekt.MainActivity;
import com.example.besammenprojekt.R;
import com.example.besammenprojekt.Chat.ChatActivity;

public class AgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
    }

// Alisa

    public void firstChat (View v) {

        // Opretter en intent til at navigere fra AgeActivity til ChatActivity
        Intent intentToFirstChat = new Intent(AgeActivity.this, ChatActivity.class);
        startActivity(intentToFirstChat);
        finish();
    }

// Rajana

        public void backButton(View view) {
            // Oprettter en intent til at navigere tilbage til MainActivity
            Intent intentToMainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentToMainActivity);
            finish();
        }

    }



