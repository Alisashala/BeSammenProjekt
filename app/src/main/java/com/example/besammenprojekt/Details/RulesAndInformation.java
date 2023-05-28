package com.example.besammenprojekt.Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.besammenprojekt.MainActivity;
import com.example.besammenprojekt.R;

import org.w3c.dom.Text;
    //AppCompatActivity-klassen er en del af Android Support-biblioteket.
    //extends --> klassen udvider eller arver funktionaliteten fra en anden klasse.
    //Det betyder, at RulesAndInformation vil arve alle de ikke-private egenskaber,
    // metoder og andre medlemmer fra AppCompatActivity.

//Rajana

public class RulesAndInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_and_information);

        // onCreate metoder kaldes når aktiviteten oprettes.
        // onCreate initialiserer brugergrænsefladen og opretter eventuelt nødvendige komponenter.
        // setContentView sætter layout til denne aktivitet ved brug af xml layout filen:
        // activity_rules_and_information.


    // String rule 1
        // Reference til TextView'en med id'et "rulesTextView" fra layout XML-filen.
        // findViewById = en metode, der søger efter en view med det specificerede id
        // i det nuværende layout.
        TextView rulesTextView = findViewById(R.id.rulesTextView);

        // Sætter detaljeret rules tekst
        String rules = "At MindTalk, we prioritize creating a safe and inclusive environment" +
                " for all users. We believe in fostering respect and empathy within our community." +
                " Here are some key guidelines and features that ensure a positive experience for " +
                "everyone:";

        // Tildeler værdien af strengen 'rules' til TextView'en: 'rulesTextView'
        rulesTextView.setText(rules);

    // String rule 2
        // Reference til TextView'en med id'et "rulesTextView" fra layout XML-filen.
        // findViewById = en metode, der søger efter en view med det specificerede id
        // i det nuværende layout.
        TextView rulesTextView2 = findViewById(R.id.rulesTextView2);

        // Sætter detaljeret rules tekst
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

        // Tildeler værdien af strengen 'rules' til TextView'en: 'rulesTextView''
        rulesTextView2.setText(rules2);

    // String rule 3
        // Reference til TextView'en med id'et "rulesTextView" fra layout XML-filen.
        // findViewById = en metode, der søger efter en view med det specificerede id
        // i det nuværende layout.
        TextView contactInformation = findViewById(R.id.informationTextView);

        // Sætter detaljeret rules tekst
        String rules3 = "We value your feedback and input. If you have any questions, concerns," +
                " or suggestions, please don't hesitate to contact us using the provided contact" +
                " information below:\n" + "\n" + "Email: Support@MindTalk.com";

        // Tildeler værdien af strengen 'rules' til TextView'en: 'rulesTextView'
        contactInformation.setText(rules3);

    }

    // onClick event
        // Håndterer onClick-event for tilbageknappen i RulesAndInformation-aktiviteten.
        // Navigerer tilbage til MainActivity.

    // Parameter view --> view, der udløser begivenheden
    public void backButtonInRules(View view) {

        // intent --> oprettelse af intent for at navigere tilbage til MainActivity
        // view.getContext() --> nuværende views kontekst bruges som det første argument til
        // Intent-konstruktøren
        Intent intentToMainActivity = new Intent(view.getContext(), MainActivity.class);

        // Intenten startes ved hjælp af metoden startActivity()
        startActivity(intentToMainActivity);

        // Afslutter den nuværende aktivitet og fjerner den fra aktivitetsstakken
        finish();
    }
}