package com.example.besammenprojekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.EmojiCompatConfigurationView;

import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private ListView lv;
    private Button send;

    private EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        send = findViewById(R.id.sendButton);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        ArrayList al = new ArrayList();
        lv = findViewById(R.id.lv);
        ed = findViewById(R.id.inputMessage);

        db.getReference("Messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Toast.makeText(ChatActivity.this, "Message added"+snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                al.add(snapshot.getValue().toString());
                ArrayAdapter adapter = new ArrayAdapter(ChatActivity.this, android.R.layout.simple_list_item_1, al);
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
}