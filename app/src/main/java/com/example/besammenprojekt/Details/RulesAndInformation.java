package com.example.besammenprojekt.Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.besammenprojekt.MainActivity;
import com.example.besammenprojekt.R;

import org.w3c.dom.Text;

public class RulesAndInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_and_information);

// String rule 1
        // Reference to the TextView with the id rulesTextView from the layout XML file.
        // findViewById = method that searches for a view with the specified id in the current layout
        TextView rulesTextView = findViewById(R.id.rulesTextView);

        // Set the detailed rules text
        String rules = "At Love And Affection, we prioritize creating a safe and inclusive environment for all users. We believe in fostering respect and empathy within our community. Here are some key guidelines and features that ensure a positive experience for everyone:";

        // Assigns the value of the 'rules' string to the TextView: 'rulesTextView'
        rulesTextView.setText(rules);

// String rule 2
        // Reference to the TextView with the id rulesTextView from the layout XML file.
        // findViewById = method that searches for a view with the specified id in the current layout
        TextView rulesTextView2 = findViewById(R.id.rulesTextView2);

        // Set the detailed rules text
        String rules2 = "• Respect & Empathy\n" +
                "• Trigger Warnings\n" +
                "• Confidentiality\n" +
                "• No Graphic Content\n" +
                "• No Discrimination\n" +
                "• Moderation\n" +
                "• Peer Support Only\n" +
                "• No Self-Harm or Suicide Discussions\n" +
                "• Reporting System\n" +
                "• Appropriate Language";

        // Assigns the value of the 'rules2' string to the TextView: 'rulesTextView2'
        rulesTextView2.setText(rules2);

// String rule 3
        // Reference to the TextView with the id informationTextView from the layout XML file.
        // findViewById = method that searches for a view with the specified id in the current layout
        TextView contactInformation = findViewById(R.id.informationTextView);

        // Set the detailed rules text
        String rules3 = "We value your feedback and input. If you have any questions, concerns, or suggestions, please don't hesitate to contact us using the provided contact information below:\n" +
                "\n" +
                "Email: Loveandaffection@livelovelaugh.dk";

        // Assigns the value of the 'rules3' string to the TextView: 'informationTextView'
        contactInformation.setText(rules3);

    }

// onClick event
    // Handles the onClick event for the back button in the RulesAndInformation activity.
    // Navigates back to the MainActivity.

    // parameter view -->  The view that triggers the event
    public void backButtonInRules(View view) {
        // Create an intent to navigate back to the MainActivity
        // // The current view's context is used as the first argument for the Intent constructor
        Intent intentToMainActivity = new Intent(view.getContext(), MainActivity.class);
        // Start the activity specified by the intent
        startActivity(intentToMainActivity);
        // Finish the current activity, removing it from the activity stack
        finish();
    }
}