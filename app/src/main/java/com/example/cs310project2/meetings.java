package com.example.cs310project2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class meetings extends AppCompatActivity {

    private Button create_btn;

    private FirebaseDatabase root;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        root = FirebaseDatabase.getInstance();
        reference = null;
        create_btn = (Button) findViewById(R.id.create_btn);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateMeeting();
            }
        });
    }

    private void CreateMeeting() {
        //create a meeting based on the info provided
        EditText locationEditText = findViewById(R.id.location_input);
        String location = locationEditText.getText().toString();
        EditText timeEditText = findViewById(R.id.time_input);
        String time = timeEditText.getText().toString();
        EditText topicEditText = findViewById(R.id.topic_input);
        String topic = topicEditText.getText().toString();
        if(validateInfo(location, time, topic)) {
            Log.d("create", "gjejfejge");
            meeting m = new meeting(location, time, topic);
            AddToDatabase(m);
            //Intent intent = new Intent(this, home.class);
            //startActivity(intent);
        } else {//meeting could not be created

        }
    }


    private Boolean validateInfo(String location, String time, String topic) {
        if(location.equals("") || time.equals("") || topic.equals("")) {
            return false;
        }

        return true;
    }

    private void AddToDatabase(meeting m) {
        String id = m.getID();
        Log.d("create", id);
        reference = root.getReference("meetings/"+id+"/host");
        reference.setValue(m.host);
        reference = root.getReference("meetings/"+id+"/location");
        reference.setValue(m.getLocation());
        reference = root.getReference("meetings/"+id+"/time");
        reference.setValue(m.getTime());
        reference = root.getReference("meetings/"+id+"/topic");
        reference.setValue(m.getTopic());
        reference = root.getReference("meetings/"+id+"/members");
        reference.setValue(m.getMembers());

    }
}
