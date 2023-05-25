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


        ImageView signOutButton = findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intentToLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentToLoginActivity);
                finish();

            }
        });
        ImageView profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent til profiloplysninger
            }
        });

        ImageView rulesAndInformation = findViewById(R.id.rulesAndInformation);
        rulesAndInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToRulesAndInformation = new Intent(getApplicationContext(), RulesAndInformation.class);
                startActivity(intentToRulesAndInformation);
                finish();
            }
        });

    }
}

