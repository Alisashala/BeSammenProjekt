package com.example.besammenprojekt;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView textView;
    private FirebaseUser user;
    private Button logOut;

    private ImageView groupChat;
    private ImageView privateChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentToMain = getIntent();
        String message = intentToMain.getStringExtra("key");
        TextView username = findViewById(R.id.userInfo);
        username.setText(message);

        auth = FirebaseAuth.getInstance();
        logOut = findViewById(R.id.logOut);
        textView = findViewById(R.id.userInfo);
        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            username.setText(message);
        }

        privateChat = findViewById(R.id.privateChat);
        privateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToPrivateChat = new Intent(view.getContext(), UserActivity.class);
                startActivity(intentToPrivateChat);
                finish();
            }
        });

        groupChat = findViewById(R.id.groupChat);
        groupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToGroupChat = new Intent(view.getContext(), AgeActivity.class);
                startActivity(intentToGroupChat);
                finish();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });








    }



}
