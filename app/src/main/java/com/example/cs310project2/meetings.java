package com.example.cs310project2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

public class meetings extends AppCompatActivity {

    private Button create_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);


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
        if(!validateInfo(location, time, topic)) {
            //meeting could not be created
        } else {
            meeting m = new meeting(location, time, topic);
            //Intent intent = new Intent(this, home.class);
            //startActivity(intent);
        }
    }


    private Boolean validateInfo(String location, String time, String topic) {
        if(location.equals("") || time.equals("") || topic.equals("")) {
            return false;
        }

        return true;
    }
}
