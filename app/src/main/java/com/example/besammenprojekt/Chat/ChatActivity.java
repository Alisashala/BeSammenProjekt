package com.example.besammenprojekt.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.besammenprojekt.Details.RulesAndInformation;
import com.example.besammenprojekt.MainActivity;
import com.example.besammenprojekt.R;
import com.example.besammenprojekt.User.AgeActivity;
import com.example.besammenprojekt.User.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class ChatActivity extends AppCompatActivity {

// Disse variabler repræsenterer forskellige elementer i brugergrænsefladen, autentifikation
// og håndtering af animationer.

    // User interface elementer / brugerfladeelementer
    private ListView lv;
    private Button send;
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




                    @NonNull
                    @Override
                    // getView()-metoden opretter og tilpasser visningen for hvert element i ListView.
                    // Den kalder først super.getView(position, convertView, parent) for at få standardvisningen.
                    // Derefter tilpasses visningen baseret på elementets position.
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                            view.setBackgroundResource(R.drawable.background_recieved_message);
                        }

                        textView.setText((String) al.get(position));
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

        startActivity(intentToAgeActivity);
        finish();
    }

    public void infoChatButton (View view) {
        Intent intentToRulesAndIndformation = new Intent(ChatActivity.this, RulesAndInformation.class);
        startActivity(intentToRulesAndIndformation);
        finish();
    }

}