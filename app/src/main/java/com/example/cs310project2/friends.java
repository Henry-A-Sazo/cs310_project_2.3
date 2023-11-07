package com.example.cs310project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class friends extends AppCompatActivity {
    public Button friends_btn;
    public Button meetings_btn;
    public Button profile_btn;
    public Button invite_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friends_btn = (Button) findViewById(R.id.friends_btn);
        friends_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFriends();
            }
        });

        meetings_btn = (Button) findViewById(R.id.meetings_btn);
        meetings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMeetings();
            }
        });

        profile_btn = (Button) findViewById(R.id.profile_btn);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });

        invite_btn = (Button) findViewById(R.id.invite_fr);
        invite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invitation();
            }
        });
    }

    public void openFriends(){
        Intent intent = new Intent(this, friends.class);
        startActivity(intent);
    }

    public void openMeetings(){
        Intent intent = new Intent(this, meetings.class);
        startActivity(intent);
    }
    public void openProfile(){
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
    public void invitation(){
        Intent intent = new Intent(this, email.class);
        startActivity(intent);
    }

}