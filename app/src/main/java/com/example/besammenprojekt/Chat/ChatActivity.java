package com.example.besammenprojekt.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.besammenprojekt.R;
import com.example.besammenprojekt.User.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
    private FirebaseAuth mAuth;
    private EditText ed;
    private KonfettiView confettiView = null;

    private Shape.DrawableShape drawableShape = null;
    private Party party = null;


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



        db.getReference("Messages").addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Toast.makeText(ChatActivity.this, "Message added"+snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                al.add(snapshot.getValue().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter(ChatActivity.this, android.R.layout.simple_list_item_1, al){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        if(position %3 == 1 || position %4 == 2) {
                            // Inflate the XML layout file containing the desired TextView
                            LayoutInflater inflater = LayoutInflater.from(getContext());
                            View layout = inflater.inflate(R.layout.activity_chat, parent, false);

                            // Find the specific TextView in the inflated layout
                            TextView textView = layout.findViewById(R.id.editListView);

                            // Set the desired background for the TextView
                            textView.setBackgroundResource(R.drawable.background_sent_message);

                            // Replace the original view's background with the inflated layout
                            view.setBackground(textView.getBackground());
                        } else {
                            view.setBackgroundResource(R.drawable.background_recieved_message);
                        }
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

}