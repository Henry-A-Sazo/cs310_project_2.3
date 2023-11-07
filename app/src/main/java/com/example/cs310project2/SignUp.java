package com.example.cs310project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;



public class SignUp extends AppCompatActivity {

    private FirebaseDatabase root;
    private DatabaseReference reference;

    public Button submit_btn;

        @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Initializing the button for sign up
        submit_btn = (Button) findViewById(R.id.sign_up_submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
    }


    public void SignUp() {

        root = FirebaseDatabase.getInstance();
        EditText emailEditText = findViewById(R.id.email_input);
        String email = emailEditText.getText().toString();

        EditText nameEditText = findViewById(R.id.name_input);
        String name = nameEditText.getText().toString();

        EditText ageEditText = findViewById(R.id.age_input);
        String age = ageEditText.getText().toString();

        EditText statusEditText = findViewById(R.id.status_input);
        String status = statusEditText.getText().toString();

        User u = new User (name, age, email, status);
        addUser(u);
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    private void addUser(User u) {
        String id = u.getID();
        reference = root.getReference("users/"+id+"/email");
        reference.setValue(u.email);
        reference = root.getReference("users/"+id+"/name");
        reference.setValue(u.name);
        reference = root.getReference("users/"+id+"/age");
        reference.setValue(u.age);
        reference = root.getReference("users/"+id+"/status");
        reference.setValue(u.status);
    }
}