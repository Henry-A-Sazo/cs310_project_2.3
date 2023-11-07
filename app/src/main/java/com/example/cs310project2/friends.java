package com.example.cs310project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class friends extends AppCompatActivity {
    public Button friends_btn;
    public Button meetings_btn;
    public Button profile_btn;
    public Button invite_btn;
    private String userName;
    public ArrayList<String> members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        //set the current user
        /*Intent intent = getIntent();
        String userName = intent.getStringExtra("user");*/
        userName = "Test";

        LinearLayout line = (LinearLayout) findViewById(R.id.line);
        //get all the meetings from the database
        members = new ArrayList<>();
        FirebaseDatabase root = FirebaseDatabase.getInstance();
        DatabaseReference reference = root.getReference("meetings");
        Context context = this;
        int count = 0;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot meetingSnapshot : snapshot.getChildren()) {
                    meeting m = meetingSnapshot.getValue(meeting.class);
                    if (m != null) {
                        members = m.getMembers();
                        //Create the text view first
                        TextView tv = new TextView(context);
                        tv.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        tv.setText(m.getMembers().get(0));
                        tv.setTypeface(null, Typeface.BOLD);
                        tv.setTextSize(20);

                        // Add the TextView to the LinearLayout
                        line.addView(tv);

                        //creating the new linearlayout for the button layout
                        // Creating the new LinearLayout for the button layout
                        LinearLayout linearLayout = new LinearLayout(context);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, // Width
                                LinearLayout.LayoutParams.WRAP_CONTENT  // Height
                        );
                        linearLayout.setLayoutParams(layoutParams);

                        // Adding the buttons
                        Button btn = new Button(context);
                        btn.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        ));
                        btn.setHeight(45);
                        btn.setText("ADD");
                        btn.setTextSize(20);
                        btn.setTextColor(Color.BLACK);
                        btn.setPadding(10, 0, 10, 5);
                        layoutParams.setMargins(10, 0, 10, 0); // Left, Top, Right, Bottom

                        ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.main_color));
                        btn.setBackgroundTintList(colorStateList);

                        // Adding the buttons
                        Button btn2 = new Button(context);
                        btn.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        ));
                        btn2.setHeight(45);
                        btn2.setText("DEL");
                        btn2.setTextSize(20);
                        btn2.setTextColor(Color.BLACK);
                        btn2.setPadding(10, 0, 10, 5);
                        layoutParams.setMargins(10, 0, 10, 0); // Left, Top, Right, Bottom

                        ColorStateList colorStateList2 = ColorStateList.valueOf(getResources().getColor(R.color.main_color));
                        btn2.setBackgroundTintList(colorStateList2);

                        // Set an OnClickListener for the button
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Implement the action you want to perform when the button is clicked here.
                                // For example, open meeting details.
                                String meetingId = "123"; // Replace with the actual meeting I
                            }
                        });
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Implement the action you want to perform when the button is clicked here.
                                // For example, open meeting details.
                                String meetingId = "123"; // Replace with the actual meeting I
                            }
                        });

                        linearLayout.addView(btn); // Add Button to LinearLayout
                        linearLayout.addView(btn2); // Add Button to LinearLayout
                        line.addView(linearLayout);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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

//        invite_btn = (Button) findViewById(R.id.invite_fr);
//        invite_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                invitation();
//            }
//        });
    }


    public void openMeetings(){
        Intent intent = new Intent(this, home.class);
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