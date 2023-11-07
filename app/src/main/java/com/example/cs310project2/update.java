package com.example.cs310project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Integer age = Integer.parseInt(ageString);
        EditText typeEditText = findViewById(R.id.status_input);
        String type = typeEditText.getText().toString();
        Boolean likesMusic = false;
        Boolean likesReading = false;
        Boolean likesSports = false;
        User temp = new User(currUser.getName(), age, currUser.getEmail(), type, likesMusic, likesReading, likesSports);
        reference.setValue(temp);

        Intent intent = new Intent(this, profile.class);
        intent.putExtra("user", currUser.getEmail());
        startActivity(intent);
    }
}