package com.example.cs310project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class update extends AppCompatActivity {

    private User currUser;
    private FirebaseDatabase root;
    private DatabaseReference reference;

    public Button update_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //get the current user information, and place it in the boxes
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user");
        root = FirebaseDatabase.getInstance();
        reference = root.getReference("users/" + userName);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currUser = snapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        update_btn = (Button) findViewById(R.id.submit_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_();
            }
        });
    }

    private void update_() {//save to database
        EditText ageEditText = findViewById(R.id.age_input);
        String ageString = ageEditText.getText().toString();
        int age;

        while (true) {
            try {
                age = Integer.parseInt(ageString);
                // Input is a valid integer, break the loop
                break;
            } catch (NumberFormatException e) {
                ageEditText.setError("Please enter a valid number");
                ageEditText.requestFocus();
                ageEditText.setText(""); // Clear the input for the next attempt
                return; // To prevent further processing if the input is not valid
            }
        }

        EditText typeEditText = findViewById(R.id.status_input);
        String type = typeEditText.getText().toString();

        while (true) {
            if (type.equalsIgnoreCase("native") || type.equalsIgnoreCase("international")) {
                break; // Input is valid, exit the loop
            } else {
                typeEditText.setError("Please enter 'Native' or 'International'");
                typeEditText.requestFocus();
                typeEditText.setText(""); // Clear the input for the next attempt
                return; // To prevent further processing if the input is not valid
            }
        }

        CheckBox music_ = findViewById(R.id.music);
        Boolean likesMusic = music_.isChecked();

        CheckBox read_ = findViewById(R.id.music);
        Boolean likesReading = read_.isChecked();

        CheckBox sports_ = findViewById(R.id.music);
        Boolean likesSports = sports_.isChecked();

        User temp = new User(currUser.getName(), age, currUser.getEmail(), type, currUser.getPassword(), likesMusic, likesReading, likesSports);
        reference.setValue(temp);

        Intent intent = new Intent(this, profile.class);
        intent.putExtra("user", currUser.getEmail());
        startActivity(intent);
    }
}