package com.example.cs310project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View; // Add this import
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast; // Add this import

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {

    public Button friends_btn;
    public Button meetings_btn;
    public Button profile_btn;

    public Button meeting1;
    public Button meeting2;
    public Button meeting3;
    public Button meeting4;


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        meeting1 = (Button) findViewById(R.id.meeting_1);
        meeting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meeting1_open();
            }
        });

        meeting2 = (Button) findViewById(R.id.meeting_2);
        meeting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meeting2_open();
            }
        });

        meeting3 = (Button) findViewById(R.id.meeting_3);
        meeting3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meeting3_open();
            }
        });

        meeting4 = (Button) findViewById(R.id.meeting_4);
        meeting4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meeting4_open();
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

    //opening the meetings
    public void meeting1_open(){
        Intent intent = new Intent(this, metting1.class);
        startActivity(intent);
    }

    public void meeting2_open(){
        Intent intent = new Intent(this, meeting2.class);
        startActivity(intent);
    }

    public void meeting3_open(){
        Intent intent = new Intent(this, meeting3.class);
        startActivity(intent);
    }

    public void meeting4_open(){
        Intent intent = new Intent(this, meeting4.class);
        startActivity(intent);
    }
}
