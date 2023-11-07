package com.example.cs310project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        User test = new User("Test", 0, "Test", "Native", false, false, false);
        reference = root.getReference("users/"+test.getEmail());
        reference.setValue(test);

        String[] inChar = {"!", "#", "$", "%", "^", "&", "*", "(", ")", "<", ">", ":", ";", "{", "}", "[", "]"};
        boolean containsInvalidCharacter = false;


        EditText emailEditText = findViewById(R.id.email_input);
        String email = emailEditText.getText().toString();

        String[] username = email.split("@");
        reference = root.getReference("users/" + username[0] + "/email");

        for (String invalid : inChar) {
            if (email.contains(invalid)) {
                containsInvalidCharacter = true;
                break;
            }
        }
            if (username[1].equals("usc.edu") && containsInvalidCharacter == false) {
                reference.setValue(email);

                EditText nameEditText = findViewById(R.id.name_input);
                String name = nameEditText.getText().toString();
                reference = root.getReference("users/" + username[0] + "/name");
                reference.setValue(name);

                EditText ageEditText = findViewById(R.id.age_input);
                String age = ageEditText.getText().toString();
                reference = root.getReference("users/" + username[0] + "/age");
                reference.setValue(age);

                EditText statusEditText = findViewById(R.id.status_input);
                String status = statusEditText.getText().toString();
                reference = root.getReference("users/" + username[0] + "/status");
                reference.setValue(status);

                Intent intent = new Intent(this, home.class);
                startActivity(intent);
            }
        //}
    }
}