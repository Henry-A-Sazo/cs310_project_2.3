package com.example.cs310project2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
public class User {

    public String name;
    public String age;
    public String email;
    public String status;
    public boolean reading;
    public boolean music;
    public boolean sports;

    private final String id;

    public String getID() {
        return id;
    }

    User (String name_, String age_, String email_, String status_/*, boolean reading_, boolean music_, boolean sports_*/) {
        name = name_;
        age = age_;
        email = email_;
        status = status_;
        /*
        reading = reading_;
        music = music_;
        sports = sports_;
        */
        id = String.valueOf(System.currentTimeMillis());
    }
}
