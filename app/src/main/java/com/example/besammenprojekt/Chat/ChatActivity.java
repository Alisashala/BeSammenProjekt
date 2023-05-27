package com.example.besammenprojekt.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.besammenprojekt.R;
import com.example.besammenprojekt.User.AgeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class ChatActivity extends AppCompatActivity {

// Disse variabler repræsenterer forskellige elementer i brugergrænsefladen, autentifikation
// og håndtering af animationer.

    // User interface elementer / brugerfladeelementer
    private ListView lv;
    private Button send;

    private EditText ed;
    private KonfettiView confettiView;
    private ProgressBar progressBar;


    // Grafik og animation
    private Party party;

    // Handler og Runnable
    private Handler handler; // Handler til udførelse af forsinkede handlinger
    private Runnable runnable; // Runnable til definerende opgaver, der skal udføres


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

// initialisering af elementer, og tilknytning til de tilsvarende variabler

        // User interface elementer / brugerfladeelementer --> initialisering
        send = findViewById(R.id.sendButton); // Knappen til at sende beskeder
        lv = findViewById(R.id.lv); // ListView til visning af beskeder
        ed = findViewById(R.id.inputMessage); // EditText til indtastning af beskeder
        confettiView = findViewById(R.id.confettiView); // KonfettiView til visning af konfetti-animation
        progressBar = findViewById(R.id.progressBar); // ProgressBar til angivelse af fremskridt

        // Database-initialisering
        FirebaseDatabase db = FirebaseDatabase.getInstance(); // Initialiserer FirebaseDatabase

        // Dataopbevaring
        ArrayList al = new ArrayList(); // ArrayListe til at gemme data

        // Initialisering af handler
        handler = new Handler(); // Handler til udførelse af forsinkede handlinger


// Runnable-ínstans
        //  En ny Runnable-instans oprettes med en implementering af run()-metoden. I dette tilfælde
        // skjuler metoden progressbaren ved at ændre dens synlighed til View.GONE.
        runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        };
        // Viser progressbaren
        progressBar.setVisibility(View.VISIBLE);

        // handler.postDelayed()-metoden bruges til at planlægge kørslen af runnable-objektet
        // med en forsinkelse på 3000 millisekunder (3 sekunder).
        handler.postDelayed(runnable, 3000);



        // Denne linje opretter en ChildEventListener, der lytter efter ændringer i child elememter
        // under "Messages" i databasen.
        db.getReference("Messages").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                // Viser en toast-besked når der tilføjes en besked
                Toast.makeText(ChatActivity.this, "Message added"+snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                // Tilføjer den modtagne besked til arraylisten
                al.add(snapshot.getValue().toString());

                // Opretter en ArrayAdapter med arraylisten og tilknytter den til ListView
                ArrayAdapter<String> adapter = new ArrayAdapter(ChatActivity.this, android.R.layout.simple_list_item_1, al){
                    @NonNull
                    @Override
                    // getView()-metoden opretter og tilpasser visningen for hvert element i ListView.
                    // Den kalder først super.getView(position, convertView, parent) for at få standardvisningen.
                    // Derefter tilpasses visningen baseret på elementets position.
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        // Skifter baggrundsfarven for hver tredje besked
                        if(position %3 == 1) {
                            // Inflater XML-layoutfilen med den ønskede TextView
                            LayoutInflater inflater = LayoutInflater.from(getContext());
                            View layout = inflater.inflate(R.layout.activity_chat, parent, false);

                            // Finder den specifikke TextView i den indflatede layout
                            TextView textView = layout.findViewById(R.id.editListView);

                            // Sætter den ønskede baggrund for TextView'en
                            textView.setBackgroundResource(R.drawable.background_sent_message);

                            // Erstatter den oprindelige views baggrund med den indflatede layout
                            view.setBackground(textView.getBackground());
                        } else {
                            // Sætter baggrundsfarven for de øvrige beskeder
                            view.setBackgroundResource(R.drawable.background_recieved_message);
                        }
                        return view;
                    }
                };
                // Tilføjer adapteren til ListView
                lv.setAdapter(adapter);
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

// onClick til sendknappen
        //Sætter en OnClickListener til knappen send og definerer dens onClick-metode
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opretter en instans af FirebaseAuth ved at kalde FirebaseAuth.getInstance().
                // Dette giver adgang til Firebase Authentication-funktionaliteterne.
                FirebaseAuth a = FirebaseAuth.getInstance();

                // Her oprettes en instans af Date ved at kalde Calendar.getInstance().getTime().
                // Dette bruges til at få den aktuelle dato og tidspunkt.
                Date getdate = Calendar.getInstance().getTime();

                // Gemmer indtastet tekst i Firebase Realtime Database under "Messages" med en unik
                // nøgle bestående af brugerens UID og det aktuelle dato og tidspunkt.
                db.getReference("Messages").child(a.getUid()+getdate).setValue(ed.getText().toString());
            }
        });

    }


// Konfettiknap


    public void showConfetti(View view) {

        // Konfigurerer en emitter, der udsender partikler med en hastighed på 1000 partikler
        // pr. sekund i en periode på 350 millisekunder.
        EmitterConfig emitterConfig = new Emitter(350, TimeUnit.MILLISECONDS).perSecond(1000);

        //Opretter en konfetti-parti ved hjælp af en PartyFactory med den konfigurerede emitter.
        // Der er yderligere indstillinger som spredning, farver og hastighedsinterval.
        party = new PartyFactory(emitterConfig)
                // konfetti farver, spread, position osv.
                .spread(700)
                .colors(Arrays.asList(0xff3660, 0x4D7429, 0xB18F6A, 0x1790d0, 0xffffba, 0xbaffc9))
                .setSpeedBetween(0f, 30f)
                .position(new Position.Relative(0.5, 0.3))
                // Bygger og returnerer et færdigt party-objekt baseret på de tidligere indstillinger.
                .build();

        // Starter konfettianimationen ved at give party-objektet til confettiView til visning
        // af konfetti.
        confettiView.start(party);
    }

// Backbutton from chatActivity til AgeActivity

    public void backButton(View view) {

        // intent --> oprettelse af intent for at navigere tilbage til AgeActivity
        Intent intentToAgeActivity = new Intent(getApplicationContext(), AgeActivity.class);
        startActivity(intentToAgeActivity);
        finish();
    }
}
