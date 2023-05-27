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

    private ListView lv;
    private Button send;
    private EditText ed;
    private KonfettiView confettiView = null;
    private Party party = null;
    private ProgressBar progressBar;
    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        send = findViewById(R.id.sendButton);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        ArrayList al = new ArrayList();
        lv = findViewById(R.id.lv);
        ed = findViewById(R.id.inputMessage);
        confettiView = findViewById(R.id.confettiView);
        progressBar = findViewById(R.id.progressBar);

        // Initialize the Handler
        handler = new Handler();
        // Create a Runnable to hide the progress bar
        runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        };


        // Show the progress bar
        progressBar.setVisibility(View.VISIBLE);
        // Schedule the runnable to hide the progress bar after 7 seconds
        handler.postDelayed(runnable, 1000);


        db.getReference("Messages").addChildEventListener(new ChildEventListener() {

            String lastMessage = "";
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Update the last message with the new message
                lastMessage = snapshot.getValue().toString();

                // Show a toast only for the last message
                if (previousChildName == null) {
                    // This is the first message, show the toast
                    Toast.makeText(ChatActivity.this, "New Message: " + lastMessage, Toast.LENGTH_SHORT).show();
                }

                al.add(0, snapshot.getValue().toString()); // Add messages at the beginning of the list
                Collections.sort(al);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatActivity.this, android.R.layout.simple_list_item_1, al) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        TextView textView = view.findViewById(android.R.id.text1);

                        if (position % 3 == 2 || position % 2 == 2)  {
                            // Apply custom background to every second TextView
                            view.setBackgroundResource(R.drawable.background_sent_message);

                        } else {
                            // Reset the background for other TextViews
                            view.setBackgroundResource(R.drawable.background_recieved_message);
                        }

                        textView.setText((String) al.get(position));
                        return view;
                    }
                };

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
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth a = FirebaseAuth.getInstance();
                Date getdate = Calendar.getInstance().getTime();
                db.getReference("Messages").child(a.getUid()+getdate).setValue(ed.getText().toString());
            }
        });
    }

    public void showConfetti(View view) {
            EmitterConfig emitterConfig = new Emitter(350, TimeUnit.MILLISECONDS).perSecond(1000);
            party = new PartyFactory(emitterConfig)
                    .spread(700)
                    .colors(Arrays.asList(0xff3660, 0x4D7429, 0xB18F6A, 0x1790d0, 0xffffba, 0xbaffc9))
                    .setSpeedBetween(0f, 30f)
                    .position(new Position.Relative(0.5, 0.3))
                    .build();

            confettiView.start(party);
        }

    //backbutton from chatActivity til AgeActivity, tjek onclick i xml filen.
    public void backButton(View view) {
        Intent intentToAgeActivity = new Intent(ChatActivity.this, AgeActivity.class);
        startActivity(intentToAgeActivity);
        finish();
    }

    public void infoChatButton (View view) {
        Intent intentToRulesAndIndformation = new Intent(ChatActivity.this, RulesAndInformation.class);
        startActivity(intentToRulesAndIndformation);
        finish();
    }

}