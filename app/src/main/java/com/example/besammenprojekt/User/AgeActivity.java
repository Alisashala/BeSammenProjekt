package com.example.besammenprojekt.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.besammenprojekt.R;
import com.example.besammenprojekt.Chat.ChatActivity;

public class AgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
    }

    public void firstChat (View v) {
        Intent toFirstChat = new Intent(AgeActivity.this, ChatActivity.class);
        startActivity(toFirstChat);
    }

}