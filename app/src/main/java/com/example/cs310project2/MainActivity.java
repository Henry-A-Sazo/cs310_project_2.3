package com.example.cs310project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public Button sign_up_btn;
    public Button sign_in_btn;

    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the button for sign up
        sign_up_btn = (Button) findViewById(R.id.signup_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUp();
            }
        });

        //For the sign in option
        sign_in_btn = (Button) findViewById(R.id.signin);
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });

    }

    //open the sign up screen
    public void openSignUp(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void openHome(){
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

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}