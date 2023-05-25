package com.example.besammenprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
    }

    public void firstChat (View v) {
        Intent intentToFirstChat = new Intent(AgeActivity.this, ChatActivity.class);
        startActivity(intentToFirstChat);
        finish();
    }


    public void backButton(View view) {
        Intent intentToMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentToMainActivity);
        finish();

    }
}