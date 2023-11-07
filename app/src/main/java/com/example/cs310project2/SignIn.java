package com.example.cs310project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    private User currUser;

    private FirebaseDatabase root;
    private DatabaseReference reference;

    public Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initializing the button for sign up
        submit_btn = (Button) findViewById(R.id.signin);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
    }

    public void SignIn() {
        EditText emailEditText = findViewById(R.id.si_email_input);
        String email = emailEditText.getText().toString();
        //reference = root.getReference("users/" + username[0] + "/name");
        String[] username = email.split("@");

        EditText passwordEditText = findViewById(R.id.si_password_input);
        String password = passwordEditText.getText().toString();
        //reference = root.getReference("users/" + username[0] + "/name");


        FirebaseDatabase root = FirebaseDatabase.getInstance();
        DatabaseReference reference = root.getReference("users/" + username);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currUser = snapshot.getValue(User.class);

                if(currUser != null) {
                    Log.d("create", "test");
                    if (password.equals(currUser.getPassword())) {
                        Intent intent = new Intent(this, metting1.class);
                        Log.d("create", "same");
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        Intent intent_ = getIntent();
//
//        String validUsername = intent_.getStringExtra("users/" + username[0]);
//        if (username[0].equals(validUsername)) {
//
//        }
    }
}
