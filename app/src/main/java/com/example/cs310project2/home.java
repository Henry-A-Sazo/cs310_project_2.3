package com.example.cs310project2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View; // Add this import
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;
import android.widget.TextView;
import android.widget.*;
import android.widget.Toast; // Add this import


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class home extends AppCompatActivity {

    private String userName;
    public ArrayList<meeting> meetingsList;
    public Button friends_btn;
    public Button meetings_btn;
    public Button profile_btn;


    TextView[] topicTextViews = new TextView[4]; // Array to hold the topic TextViews
    Button[] meetingButtons = new Button[4]; // Array to hold the meeting detail Buttons


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //set the current user
        /*Intent intent = getIntent();
        String userName = intent.getStringExtra("user");*/
        userName = "Test";

        LinearLayout line = (LinearLayout) findViewById(R.id.line);
        //get all the meetings from the database
        meetingsList = new ArrayList<>();
        FirebaseDatabase root = FirebaseDatabase.getInstance();
        DatabaseReference reference = root.getReference("meetings");
        Context context = this;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for (DataSnapshot meetingSnapshot : snapshot.getChildren()) {
                    meeting m = meetingSnapshot.getValue(meeting.class);
                    if (m != null) {
                        meetingsList.add(m);
                        TextView tv = new TextView(context);
                        tv.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        tv.setText(m.getTopic());
                        tv.setTypeface(null, Typeface.BOLD);

                        // Add the TextView to the LinearLayout
                        line.addView(tv);

                        // Create Button
                        Button btn = new Button(context);
                        btn.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        btn.setText("See Meeting Details");
                        btn.setTag(m.getID());
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String meetingId = (String) v.getTag();
                                //Go to a meeting details view, passing in meeting and user ID
                                openDetails(meetingId);
                            }
                        });
                        line.addView(btn); // Add Button to LinearLayout
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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
    }


    public void openFriends() {
        Intent intent = new Intent(this, friends.class);
        intent.putExtra("user", userName);
        startActivity(intent);
    }


    public void openMeetings() {
        Intent intent = new Intent(this, meetings.class);
        intent.putExtra("user", userName);
        startActivity(intent);
    }

    public void openProfile() {
        Intent intent = new Intent(this, profile.class);
        intent.putExtra("user", userName);
        startActivity(intent);
    }

    public void openDetails(String Id) {
        Intent intent = new Intent(this, metting1.class);
        intent.putExtra("id", Id);
        intent.putExtra("user", userName);
        startActivity(intent);
    }

}
