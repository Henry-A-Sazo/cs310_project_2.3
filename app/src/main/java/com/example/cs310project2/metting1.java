package com.example.cs310project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class metting1 extends AppCompatActivity {

    private meeting m;
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the current user and meeting
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user");
        String mID = intent.getStringExtra("id");

        //get the user first
        FirebaseDatabase root = FirebaseDatabase.getInstance();
        DatabaseReference reference = root.getReference("users/" + userName);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currUser = snapshot.getValue(User.class);
                if(currUser != null) {


                    TextView topic = (TextView) findViewById(R.id.topic);
                    topic.setText(m.getTopic());
                    TextView time = (TextView) findViewById(R.id.time);
                    time.setText(m.getTime());
                    TextView location = (TextView) findViewById(R.id.location);
                    location.setText(m.getLocation());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //now the meeting
        reference = root.getReference("meetings/" + mID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                m = snapshot.getValue(meeting.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metting1);
    }
}