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

public class UserActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

    }
        // onClick event
        // Handles the onClick event for the back button in the RulesAndInformation activity.
        // Navigates back to the MainActivity.

        // parameter view -->  The view that triggers the event
        public void backButtonInUser(View view) {
            // Create an intent to navigate back to the MainActivity
            // // The current view's context is used as the first argument for the Intent constructor
            Intent intentToMainActivity = new Intent(view.getContext(), MainActivity.class);
            // Start the activity specified by the intent
            startActivity(intentToMainActivity);
            // Finish the current activity, removing it from the activity stack
            finish();
        }
    }
